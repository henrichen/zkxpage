zkXPage = {};

zkXPage.jqid =  function(id) {
	if(typeof(id)=='string'){
		id = id.replace(/:/g, "\\:");
	}
	return id;
};

zkXPage.detach = function(widgetid){
	var w = zk.Widget.$(zkXPage.jqid(widgetid));
	if(w){
		w.detach();
	}
}
