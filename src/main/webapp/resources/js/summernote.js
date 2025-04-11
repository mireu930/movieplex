$('#boardContents').summernote({
    height: 400,
    callbacks: {
        onImageUpload: function (files) {
            console.log(files[0]); // 업로드된 파일 확인
            let f = new FormData();
            f.append("uploadFile", files[0]);

            fetch('./detailFiles', {
                method: 'POST',
                body: f
            })
            .then(response => response.text())
            .then(url => {
                $(boardContents).summernote('editor.insertImage', url.trim());
            })
            .catch(error => console.error("Error uploading file:", error));
        },

        onMediaDelete: function (files) {
            let fileUrl = files[0].src;
            console.log("삭제할 파일 URL:", fileUrl);

            let fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            console.log("삭제할 파일 이름:", fileName);

            let f = new FormData();
            f.append("fileName", fileName);

            fetch("./detailFilesDelete", {
                method: 'POST',
                body: f
            })
            .then(response => response.text())
            .then(result => console.log("파일 삭제 응답:", result))
            .catch(error => console.error("Error deleting file:", error));
        }
    }
});