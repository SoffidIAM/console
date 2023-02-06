zkUploader={};
zkUploader.init=function(ed) {
	var input = document.getElementById(ed.id+"!input");
	input.uploader = ed;
	ed.pendingFiles = [];
	zk.listen(input, "change", zkUploader.upload);
	zk.listen(document.getElementById(ed.id+"!cancel"), "click", zkUploader.cancel);
};

zkUploader.upload=function(ev) {
	var input = ev.target;
    var files = input.files;
	var uploader = document.getElementById($uuid(input));
    for (var filenum = 0; filenum < files.length; filenum ++) {
		uploader.pendingFiles.push(files[filenum]);
    }
    input.value = "";
    zk.progress();
	zkUploader.uploadeNext(uploader);
}

zkUploader.cancel=function(event) {
	var uploader = document.getElementById($uuid(event.target));
	zkau.send ({uuid: uploader.id, cmd: "onClose", data : []}, 5);		
}

zkUploader.uploadeNext=function(uploader) {
	var file = uploader.pendingFiles.shift();
	if (file == null) {
		zk.progressDone();
		zkau.send ({uuid: uploader.id, cmd: "onUpload", data : []}, 5);		
		return;
	}

    const formData  = new FormData();
    formData.append("uuid", new Blob([uploader.id], {type: "text/plain"}));
    formData.append("file", file);
	
    const fileName = file.name;
	
	zk.progress();
	var progress = document.getElementById("zk_loading");
	var msg = progress.firstElementChild.lastChild;
	msg.textContent = "Uploading "+fileName;
	
    let request = new XMLHttpRequest();
    
    var url = zk.getUpdateURI("/uploader");
    request.open('POST', url);
    request.setRequestHeader ("Content-Type", "multipart/form-data");
    request.setRequestHeader ("Accept", "application/json");
    request.upload.addEventListener('progress', function(e) {
    	let percent_completed = (e.loaded / e.total)*100;
		zk.progress();
		var progress = document.getElementById("zk_loading");
		var msg = progress.firstElementChild.lastChild;
		msg.textContent = "Uploading "+fileName+ " "+ percent_completed.toFixed(1) + " %";
    });
    request.addEventListener ("load", (e) => {
		zkUploader.uploadeNext(uploader);
    });
    request.addEventListener ("error", (e) => {
		zk.progressdone();
		uploader.pendingFiles = [];
    });
    request.send(formData);
}

