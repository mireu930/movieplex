package com.movie.plex.movies;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MovieJsonController {
	@Value("${tmdb.apiKey}")
	private String tmdbApiKey;
	
	@RequestMapping(value="/test")
	public void movieJsonTest() throws Exception{

		

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");

//			ResponseEntity<String> responseEntity = restTemplate
//					.getForEntity("https://api.themoviedb.org/3/discover/movie?api_key=3c5060d60049b6d4659d85b884d76adb&region=KR&language=ko-KR&page=2&primary_release_date.gte=2025-02-25&with_original_language=ko&include_adult=false&include_video=false", String.class);
		// 먼저 총 페이지를 알기 위해 불러오기
		String jsonString = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?" + "api_key="
				+ tmdbApiKey + "&region=KR&language=ko-KR" + "&primary_release_date.gte=2025-02-25"
				+ "&with_original_language=ko" + "&include_adult=false&include_video=false", String.class);

		// System.out.println(jsonString);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

		// 총 페이지 수
		Long totalPage = (Long) jsonObject.get("total_pages");

		List<MovieDTO> dtos = new ArrayList<MovieDTO>();
		// System.out.println(totalPage);
		for (int i = 1; i <= (long) totalPage; i++) {
			// System.out.println(i);
			String jsons = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie" + "?api_key="
					+ tmdbApiKey + "&region=KR" + "&language=ko-KR" + "&primary_release_date.gte=2025-02-25"
					+ "&with_original_language=ko" + "&include_adult=false&include_video=false" + "&page=" + i,
					String.class);
			JSONObject parse = (JSONObject) jsonParser.parse(jsons);
			// System.out.println(parse);
			JSONArray result = (JSONArray) parse.get("results");
			for (Object obj : result) {
				JSONObject dto = (JSONObject) obj;
				MovieDTO movieDTO = new MovieDTO();

				// 영화 고유 id
				movieDTO.setMovieId(Long.parseLong(dto.get("id").toString()));

				String video = restTemplate.getForObject("https://api.themoviedb.org/3/movie" + "/"
						+ dto.get("id").toString() + "/videos" + "?api_key=" + tmdbApiKey, String.class);
				JSONObject videoParse = (JSONObject) jsonParser.parse(video);
				//System.out.println(video);
				JSONArray videoResult = (JSONArray) videoParse.get("results");
				if(videoResult.size() != 0) {
					JSONObject v = (JSONObject) videoResult.get(0);
					movieDTO.setVideo(v.get("key").toString());
				}
				

				// 영화 제목
				movieDTO.setMovieTitle(dto.get("title").toString());

				// 세로 포스터
				if (dto.get("poster_path") != null) {
					movieDTO.setShortPoster(dto.get("poster_path").toString());
				}

				// 가로 포스터
				if (dto.get("backdrop_path") != null) {
					movieDTO.setLongPoster(dto.get("backdrop_path").toString());
				}

				// String(tmdb) => Date로 변환
				String date = (String) dto.get("release_date");
				Date releaseDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				java.util.Date utilDate = sdf.parse(date);
				releaseDate = new Date(utilDate.getTime());
				movieDTO.setReleaseDate(releaseDate);

				// 인기
				movieDTO.setPopularity(Double.parseDouble(dto.get("popularity").toString()));

				// 줄거리
				movieDTO.setOverView(dto.get("overview").toString());

				// 영화 티켓값
				movieDTO.setTicketPrice(12000L);

				// 영화 삭제 여부
				movieDTO.setMovieCheck(0L);
				//System.out.println(movieDTO.getMovieId());
				dtos.add(movieDTO);
			}

		}
	}
}
