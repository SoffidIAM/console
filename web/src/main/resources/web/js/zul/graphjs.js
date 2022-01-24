Chart.register(ChartDataLabels);

Chart.register({
	id: "centerLabel",
	beforeDraw: function (chart) {
	    if (chart.config.options.elements && chart.config.options.elements.center) {
	      //Get ctx from string
	      var ctx = chart.ctx;

	      //Get options from the center object in options
	      var centerConfig = chart.config.options.elements.center;
	      var fontStyle = centerConfig.fontStyle || 'Arial';
	      var txt = centerConfig.text;
	      var color = centerConfig.color || '#000';
	      var sidePadding = centerConfig.sidePadding || 20;
	      var sidePaddingCalculated = (sidePadding/100) * (chart.innerRadius * 2)
	      //Start with a base font of 30px
	      ctx.font = "30px " + fontStyle;

	      //Get the width of the string and also the width of the element minus 10 to give it 5px side padding
	      var stringWidth = ctx.measureText(txt).width;
	      var elementWidth = (chart.innerRadius * 2) - sidePaddingCalculated;

	      // Find out how much the font can grow in width.
	      var widthRatio = elementWidth / stringWidth;
	      var newFontSize = Math.floor(30 * widthRatio);
	      var elementHeight = (chart.innerRadius * 2);

	      // Pick a new font size so it will not be larger than the height of label.
	      var fontSizeToUse = Math.min(newFontSize, elementHeight);

	      //Set font settings to draw it correctly.
	      ctx.textAlign = 'center';
	      ctx.textBaseline = 'middle';
	      var centerX = ((chart.chartArea.left + chart.chartArea.right) / 2);
	      var centerY = ((chart.chartArea.top + chart.chartArea.bottom) / 2);
	      ctx.font = fontSizeToUse+"px " + fontStyle;
	      ctx.fillStyle = color;

	      //Draw text in center
	      ctx.fillText(txt, centerX, centerY);
	    }
	  }
	});

	
zkGraphjs={};

zkGraphjs.setData=function(chart, data) {
    myData = JSON.parse(data);
	chart.data = myData;
    if (myData.options && myData.options.scales && 
    		myData.options.scales.yAxes &&
    		myData.options.scales.yAxes[0] &&
    		myData.options.scales.yAxes[0].ticks &&
    		myData.options.scales.yAxes[0].ticks.callback &&
    		myData.options.scales.yAxes[0].ticks.callback == "locale")
  		{
    	myData.options.scales.yAxes[0].ticks.callback = 
   			function(value, index, values) {
					return value.toLocaleString(); 
			};
  		}

	var canvas = chart.firstElementChild;
	canvas.width = chart.offsetWidth;
	canvas.height = chart.offsetHeight;

	var ctx = canvas.getContext('2d');

    var theChart = chart.theChart;
	if (theChart) {
		if (myData.data.datasets.length == theChart.data.datasets.length) {
			if (myData.data.labels) theChart.data.labels = myData.data.labels;
			for (var i = 0; i < myData.data.datasets.length; i++)
				theChart.data.datasets[i].data = myData.data.datasets[i].data;
		} else {
			theChart.data = myData.data;				
		}
		theChart.options = myData.options;
		theChart.update();
		theChart.width = chart.offsetWidth;
		theChart.height = chart.offsetHeight;
		
		window.addEventListener("resize", (event) => {
			var ctx = canvas.getContext('2d');
			chart.theChart.destroy();
			chart.theChart = new Chart(ctx, chart.data);
		});
	} else {
		var theChart = new Chart(ctx, myData);
		chart.theChart = theChart;
	}
    return myData;
}

zkGraphjs.startTimer=function(ed) {
	if ( ed.getAttribute("refresh")) {
		setTimeout(function() {
			zkau.send({
	            uuid: $uuid(ed),
	            cmd: "onRefresh",
	            data: [],
				ignorable: true
		    }, 0)},
		Number(ed.getAttribute("refresh")) );
	}
}

zkGraphjs.init=function(ed) {
	if (ed.getAttribute("data") != null) {
		zkGraphjs.setData(ed, ed.getAttribute("data"));
	}
	zkGraphjs.startTimer(ed);
};

zkGraphjs.cleanup=function(_2){
};

zkGraphjs.setAttr = function (ed, name, value) {
	if (name == "data") {
		zkGraphjs.setData(ed,value);
		return true;		
	}
	else if (name == "refresh") {
		ed.setAttribute("refresh", value);
		return true;		
	}
	else
		return false;
}

