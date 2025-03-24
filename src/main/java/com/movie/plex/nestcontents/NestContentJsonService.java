package com.movie.plex.nestcontents;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class NestContentJsonService {
	
	@Autowired
	private NestContentDAO nestContentDAO;
	
	@Value("${tmdb.apiKey}")
	private String tmdbApiKey;
	
	public int addJsonList() throws Exception{
		
		RestTemplate  restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		
		String jsonString = restTemplate.getForObject(
				"https://api.themoviedb.org/3/discover/movie?"
						+  "api_key=" + tmdbApiKey
						+ "&region=KR&language=ko-KR"
						+ "&primary_release_date.gte=2025-02-25"
						+ "&with_original_language=ko"
						+ "&with_original_language=en"
						+ "&include_adult=false&include_video=false",
						String.class);
		
		System.out.println(jsonString);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
		
		Long totalPage = (Long)jsonObject.get("total_pages");
		
		List<NestContentDTO> nestContentDTOs = new ArrayList<NestContentDTO>();
		for(int i=1; i<=(long) totalPage; i++) {
			String jsons = restTemplate.getForObject(
					 "https://api.themoviedb.org/3/discover/movie"
							    +  "?api_key=" + tmdbApiKey
							    + "&region=KR"
							    + "&language=ko-KR"
							    + "&primary_release_date.gte=2025-02-25"
							    + "&with_original_language=ko"
							    + "&with_original_language=en"
							    + "&include_adult=false&include_video=false"
							    + "&page=" + i,
							    String.class);
			
			JSONObject parse = (JSONObject) jsonParser.parse(jsons);
			JSONArray result = (JSONArray) parse.get("results");
			
			for(Object obj : result) {
				JSONObject dto = (JSONObject) obj;
				NestContentDTO nestContentDTO = new NestContentDTO();
				
				nestContentDTO.setContentID(Long.parseLong(dto.get("id").toString()));
				
				nestContentDTO.setContentTitle(dto.get("title").toString());
				
				if(dto.get("poster_path") != null) {
					nestContentDTO.setShortPoster(dto.get("poster_path").toString());
				}
				
				if(dto.get("backdrop_path") != null) {
					nestContentDTO.setLongPoster(dto.get("backdrop_path").toString());
				}
				
				String date =(String)dto.get("release_date");
				
				Date releaseDate = null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				java.util.Date utilDate = sdf.parse(date);
				
				releaseDate = new Date(utilDate.getTime());
				
				nestContentDTO.setReleaseDate(releaseDate);
				
				nestContentDTO.setPopularity(Double.parseDouble(dto.get("popularity").toString()));
				
				nestContentDTO.setOverView(dto.get("overview").toString());
				
				nestContentDTO.setKind(Long.parseLong(dto.get("kind").toString()));
				
				System.out.println(nestContentDTO.getContentId());
				nestContentDTOs.add(nestContentDTO);
				
			}
				
		}
	
		return nestContentDAO.addJsonList(nestContentDTOs);
	
	}
}
