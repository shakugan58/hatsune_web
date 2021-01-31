//---------------------------------------------------
var data = [];
var edges = [];
var paint_interval;
var click_number = 0;
var nGeneration_mechanism = 'ER_network';
var nDestruction_mechanism = 'none';
var normolSpeed = 500;
var SpeedOfPainting = normolSpeed;
var symbol_size = 15;
var nNodes_color_step_1 = "#f3051a";
var nNodes_color_step_2 = "#f66516";
var nNodes_color_step_3 = "#f8f402";
var nNodes_color_step_4 = "#65f60a";
var nNodes_color_step_5 = "#0a31f6";
var nNodes_color_step_6 = "#ee0af6";
var nNodes_density = 0.1;
var nEdges_color = "#000000";
var nEdges_width = 2;
var nEdges_style = 'solid';
var nEdges_length = 30;
var nFrame_select = 1;
var nNodes_number = 10;
var nEdge_probability = 0.5
var nInit_edge_number = 9;
var nInit_node_number = 10;
var nRewiring_probability = 0.1;
var nDegree_increase = 6;
var nNodes_delete_id = -1;
var nEdges_delete_id1 = -1;
var nEdges_delete_id2 = -1;
var nEdges_add_id1 = -1;
var nEdges_add_id2 = -1;
var myChart = echarts.init(document.getElementById('main'));
var myChart_sta_chart = echarts.init(document.getElementById('sta_chart'));
var url = "/practice_system/Test1";
var para = [];
//----------------------------------------------------

function set_option() {
    var option;
    option = {
        tooltip:{
            trigger:'item',
            triggerOn:'mousemove',
            hideDelay: 2000,
            animation:true,
            animationThreshold:200,

        },
        series: [{
            type: 'graph',
            layout: 'force',
            animation: false,
            hoverAnimation:true,
            data: data,
            roam: true,
            force: {
                // initLayout: 'circular'
                gravity: nNodes_density,
                repulsion: 100,
                edgeLength: nEdges_length
            },
            focusNodeAdjacency:true,
            legendHoverLink: true,
            edges: edges,
            emphasis: {
                lineStyle: {
                    width: 8*nEdges_width
                }
            },
            itemStyle: {
                shadowBlur: symbol_size/8,
                color: {
                    type: 'linear',
                    x: 0,
                    y: 0,
                    x2: 0,
                    y2: 1,
                    colorStops: [{
                        offset: 0, color: nNodes_color_step_1 // 0% 处的颜色
                    }, {
                        offset: 0.2, color: nNodes_color_step_2 // 20% 处的颜色
                    }, {
                        offset: 0.4, color: nNodes_color_step_3 // 40% 处的颜色
                    }, {
                        offset: 0.6, color: nNodes_color_step_4 // 60% 处的颜色
                    }, {
                        offset: 0.8, color: nNodes_color_step_5 // 80% 处的颜色
                    }, {
                        offset: 1, color: nNodes_color_step_6 // 100% 处的颜色
                    }],
                    global: false, // 缺省为 false
                    //repeat: 'repeat' // 是否平铺，可以是 'repeat-x', 'repeat-y', 'no-repeat'
                },
            }
        }]
    };
    myChart.setOption(option);
}


function cont_stop_Time(){
    var buttonEle2 = document.getElementById("button2");
    if (click_number % 2 === 0){
        buttonEle2.innerHTML="暂停";
        paint_interval = painting();
    }
    else{
        buttonEle2.innerHTML="继续";
        clearInterval(paint_interval);
    }
    click_number++;
}


function reset(){
    var buttonEle2 = document.getElementById("button2");
    clearInterval(paint_interval);
    if(nGeneration_mechanism === 'none'){
        Options.Frame_select = 1;
        nFrame_select = 1;
        gui.updateDisplay();
        url_operation2();
        set_option();
        setTimeout("paint_interval = painting()",SpeedOfPainting);
    }
    else{
        if(nGeneration_mechanism === 'ER_network'){
            /*para = [];
            para.push(nNodes_number);
            para.push(nEdge_probability);*/
            para = [nNodes_number,nEdge_probability,-1];
        }
        else if(nGeneration_mechanism === 'WS_network'){
            /*para = [];
            para.push(nNodes_number);
            para.push(nInit_edge_number);
            para.push(nRewiring_probability);*/
            para = [nNodes_number,nInit_edge_number,nRewiring_probability];
        }
        else if(nGeneration_mechanism === 'BA_network'){
            /*para = [];
            para.push(nNodes_number);
            para.push(nInit_node_number);
            para.push(nDegree_increase);*/
            para = [nNodes_number,nInit_node_number,nDegree_increase];

        }

        Options.Frame_select = 1;
        nFrame_select = 1;
        gui.updateDisplay();

        url_operation();
        /*var testdata = {"nodes":"0 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9","links":"0 ,2 ,0 ,3 ,0 ,4 ,0 ,5 ,0 ,6 ,0 ,8 ,1 ,2 ,1 ,3 ,1 ,7 ,2 ,3 ,2 ,7 ,2 ,8 ,3 ,5 ,3 ,7 ,3 ,8 ,4 ,6 ,4 ,9","degree_dis":"0.0 ,0.10000000149011612 ,0.20000000298023224 ,0.4000000059604645 ,0.0 ,0.10000000149011612 ,0.20000000298023224 ,0.0 ,0.0 ,0.0","avrLen":1.1272727272727272,"cc":0.6733333333333333,"noteNum":10,"sideNum":34,"fr":5};
        testdata = JSON.stringify(testdata);
        supertest(testdata);*/
        set_option();
        setTimeout("paint_interval = painting()",SpeedOfPainting);
    }

    buttonEle2.innerHTML="暂停";
    click_number = 1;
}

function url_operation(){
    myChart.showLoading();
    $.post(
        url,
        //{"type":nGeneration_mechanism,"para":para,"frame":nFrame_select},
        {"type":nGeneration_mechanism,"para0":para[0],"para1":para[1],"para2":para[2],"frame":nFrame_select},
        function(data) {
            myChart.hideLoading();
            let jsonArray = JSON.parse(data);
            let nodes = jsonArray.nodes.split(" ,");
            let links = jsonArray.links.split(" ,");
            let degree_dis = jsonArray.degree_dis.split(" ,");

            let avrLen = parseFloat(jsonArray.avrLen);
            let cc = parseFloat(jsonArray.cc);
            let noteNum = parseInt(jsonArray.noteNum);
            let sideNum = parseInt(jsonArray.sideNum);
            let fr = parseFloat(jsonArray.fr);

            let para = [avrLen,cc,noteNum,sideNum,fr];

            after_url(nodes,links,degree_dis,para);
        });

}

function url_operation2(){
    myChart.showLoading();
    $.post(
        url,
        {"type":nDestruction_mechanism,"frame":nFrame_select},
        function(data) {
            myChart.hideLoading();
            let jsonArray = JSON.parse(data);
            let nodes = jsonArray.nodes.split(" ,");
            let links = jsonArray.links.split(" ,");

            let degree_dis = jsonArray.degree_dis.split(" ,");

            let avrLen = parseFloat(jsonArray.avrLen);
            let cc = parseFloat(jsonArray.cc);
            let noteNum = parseInt(jsonArray.noteNum);
            let sideNum = parseInt(jsonArray.sideNum);
            let fr = parseFloat(jsonArray.fr);

            let para = [avrLen,cc,noteNum,sideNum,fr];

            after_url(nodes,links,degree_dis,para);
        });

}

function url_operation3(type,par){
    myChart.showLoading();
    $.post(
        url,
        {"type":type,"para0":par[0],"para1":par[1]},
        function(data) {
            myChart.hideLoading();
            let jsonArray = JSON.parse(data);
            let nodes = jsonArray.nodes.split(" ,");
            let links = jsonArray.links.split(" ,");
            let degree_dis = jsonArray.degree_dis.split(" ,");
            let avrLen = parseFloat(jsonArray.avrLen);
            let cc = parseFloat(jsonArray.cc);
            let noteNum = parseInt(jsonArray.noteNum);
            let sideNum = parseInt(jsonArray.sideNum);
            let fr = parseFloat(jsonArray.fr);

            let para = [avrLen,cc,noteNum,sideNum,fr];

            after_url(nodes,links,degree_dis,para);
        });
}

function after_url(nodes,links,degree_dis,para){
    data = [];
    edges = [];

    for(let i = 0; i < nodes.length; i++){
        data.push({
            id: nodes[i] ,
            symbolSize: symbol_size,
            value: nodes[i]
        });
    }
    for(let i = 0; i < links.length; i=i+2){
        edges.push({
            lineStyle:{
                color: nEdges_color,
                width: nEdges_width,
                type: nEdges_style
            },
            source: links[i],
            target: links[i+1],
            value:'between '+links[i]+' and '+links[i+1]
        });
    }

    paintOur_sta_chart(degree_dis);
    document.getElementById("ave_pathlegth-count").innerHTML = Math.round(para[0]*100000)/100000;
    document.getElementById("Clustering_coefficient-count").innerHTML = Math.round(para[1]*100000)/100000;
    document.getElementById("Nodes_num-count").innerHTML = para[2];
    document.getElementById("Edge_num-count").innerHTML = para[3]/2;
    document.getElementById("current_frame-count").innerHTML = para[4];
}

function painting(){
    return setInterval(function() {

        nFrame_select = nFrame_select + 1;
        Options.Frame_select = nFrame_select;
        gui.updateDisplay();
        if(nGeneration_mechanism === 'none'){
            url_operation2();
        }
        else{
            url_operation();
        }

        set_option();

        // console.log('nodes: ' + data.length);
        // console.log('links: ' + data.length);
    }, SpeedOfPainting);
}

function paintOur_sta_chart(para){
    var xdata = [];
    var ydata = para;
    for(var i = 0; i < para.length; i ++){
        xdata[i] = i;
    }
    option = null;
    option = {
        xAxis: {
            type: 'category',
            data: xdata
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: ydata,
            type: 'line'
        }]
    };
    myChart_sta_chart.setOption(option);
}

/*
function supertest(data){
	let jsonArray = JSON.parse(data);
            let nodes = jsonArray.nodes.split(" ,");
            let links = jsonArray.links.split(" ,");
            let degree_dis = jsonArray.degree_dis.split(" ,");
            let avrLen = parseFloat(jsonArray.avrLen);
            let cc = parseFloat(jsonArray.cc);
            let noteNum = parseInt(jsonArray.noteNum);
            let sideNum = parseInt(jsonArray.sideNum);
            let fr = parseFloat(jsonArray.fr);

            let para = [avrLen,cc,noteNum,sideNum,fr];

            after_url(nodes,links,degree_dis,para);
}
*/
