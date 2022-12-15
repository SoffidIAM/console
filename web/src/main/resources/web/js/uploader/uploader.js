zkUploader={};
zkUploader.init=function(ed) {
	var input = document.getElementById(ed.id+"!input");
	input.uploader = ed;
	ed.pendingFiles = [];
	zk.listen(input, "change", zkUploader.upload);
	var cancel = document.getElementById(ed.id+"!cancel");
	if (cancel)
		zk.listen(cancel, "click", zkUploader.cancel);
};

zkUploader.upload=function(ev) {
	var input = ev.target;
    var files = input.files;
	var uploader = document.getElementById($uuid(input));
    for (var filenum = 0; filenum < files.length; filenum ++) {
		uploader.pendingFiles.push(files[filenum]);
    }
    input.value = "";
	zkUploader.uploadeNext(uploader);
}

zkUploader.cancel=function(event) {
	var uploader = document.getElementById($uuid(event.target));
	zkau.send ({uuid: uploader.id, cmd: "onClose", data : []}, 5);		
}

zkUploader.uploadeNext=function(uploader) {
	var file = uploader.pendingFiles.shift();
	if (file == null) {
		var p = document.getElementById("uploadprog");
		if (p) p.remove();
		zkau.send ({uuid: uploader.id, cmd: "onUpload", data : []}, 5);		
		return;
	}

    const formData  = new FormData();
    formData.append("uuid", new Blob([uploader.id], {type: "text/plain"}));
    formData.append("file", file);
	formData.append("name", file.name);
    const fileName = file.name;
	
	var p = document.getElementById("uploadprog");
	if (p) p.remove();
	AU_progressbar("uploadprog", "Uploading "+fileName, zk.booting);
	
    let request = new XMLHttpRequest();
    
    var url = zk.getUpdateURI("/uploader");
    request.open('POST', url);
    request.setRequestHeader ("Content-Type", "multipart/form-data");
    request.setRequestHeader ("Accept", "application/json");
    request.upload.addEventListener('progress', function(e) {
    	let percent_completed = (e.loaded / e.total)*100;
		var msg = "Uploading "+fileName+ " "+ percent_completed.toFixed(0) + " %";
		var p = document.getElementById("uploadprog");
		if (p) p.remove();
		AU_progressbar("uploadprog", msg, zk.booting);
    });
    request.addEventListener ("load", (e) => {
		zkUploader.uploadeNext(uploader);
    });
    request.addEventListener ("error", (e) => {
		var p = document.getElementById("uploadprog");
		if (p) p.remove();
		uploader.pendingFiles = [];
    });
    request.send(formData);
}

