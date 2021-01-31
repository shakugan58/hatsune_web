let total_frame_number = 1000;
let Options = new function () {
    this.Generation_mechanism = 'ER_network';
    this.Destruction_mechanism = 'none';
    this.Speed_select = '1.0';
    this.Frame_select = 1;
    this.Nodes_size_select = 15;
    this.Nodes_color_step_1 = "#f3051a";
    this.Nodes_color_step_2 = "#f66516";
    this.Nodes_color_step_3 = "#f8f402";
    this.Nodes_color_step_4 = "#65f60a";
    this.Nodes_color_step_5 = "#0a31f6";
    this.Nodes_color_step_6 = "#ee0af6";
    this.Nodes_density = 0.05;
    this.Edges_color = "#000000";
    this.Edges_width = 2;
    this.Edges_style = 'solid';
	this.Paint_layout = 'force';
    this.Edges_length = 30;
    this.Nodes_number = 10;
    this.Edge_probability = 0.5;
    this.Init_edge_number = 9;
    this.Init_node_number = 10;
    this.Rewiring_probability = 0.1;
    this.Degree_increase = 6;
	
    this.Nodes_delete_id = -1;
    this.Edges_delete_id1 = -1;
    this.Edges_delete_id2 = -1;
    this.Edges_add_id1 = -1;
    this.Edges_add_id2 = -1;
};

let Options_button = new function(){
    this.Nodes_delete_yes_button = function(){
        let this_type = 'Nodes_delete';
        let this_para = [nNodes_delete_id,-1];
        url_operation3(this_type,this_para);
        set_option();
    };

    this.Edges_delete_yes_button = function(){
        let this_type = 'Edges_delete';
        let this_para = [nEdges_delete_id1,nEdges_delete_id2];
        url_operation3(this_type,this_para);
        set_option();
    };

    this.Nodes_add_yes_button = function(){
        let this_type = 'Nodes_add';
        let this_para = [-1,-1];
        url_operation3(this_type,this_para);
        set_option();
    };

    this.Edges_add_yes_button = function(){
        let this_type = 'Edges_add';
        let this_para = [nEdges_add_id1,nEdges_add_id2];
        url_operation3(this_type,this_para);
        set_option();
    };
};

let gui = new dat.GUI({width:400});
gui.domElement.id = 'gui';

let vGeneration_mechanism = gui.add(Options,'Generation_mechanism',['ER_network','WS_network','BA_network','none']);
let vDestruction_mechanism = gui.add(Options,'Destruction_mechanism',['Degree_centrality','betweenness_centrality','Closeness_centrality','eigenvector_centrality','Katz_centrality','PageRank','none']);
const Folder1 = gui.addFolder('Parameter_configuration');
const vNodes_number = Folder1.add(Options,'Nodes_number').min(1).step(1);
const Folder10 = Folder1.addFolder('used in ER_network');
const vEdge_probability = Folder10.add(Options,'Edge_probability').min(0).max(1);
const Folder11 = Folder1.addFolder('used in WS_network');
const vInit_edge_number = Folder11.add(Options,'Init_edge_number').min(1).step(1);
const vRewiring_probability = Folder11.add(Options,'Rewiring_probability').min(0).max(1);
const Folder12 = Folder1.addFolder('used in BA_network');
const vInit_node_number = Folder12.add(Options,'Init_node_number').min(1).step(1);
const vDegree_increase = Folder12.add(Options,'Degree_increase').min(1).step(1);
Folder1.open();
Folder10.open();


let vSpeed_select = gui.add(Options,'Speed_select',['0.5','1.0','1.5','2.0','3.0','5.0']);
let vFrame_select = gui.add(Options,'Frame_select',1,total_frame_number,1);

const Folder2 = gui.addFolder('Nodes_configuration');
let vNodes_size_select = Folder2.add(Options,'Nodes_size_select',1,40,1);
let vNodes_color_step_1 = Folder2.addColor(Options,'Nodes_color_step_1');
let vNodes_color_step_2 = Folder2.addColor(Options,'Nodes_color_step_2');
let vNodes_color_step_3 = Folder2.addColor(Options,'Nodes_color_step_3');
let vNodes_color_step_4 = Folder2.addColor(Options,'Nodes_color_step_4');
let vNodes_color_step_5 = Folder2.addColor(Options,'Nodes_color_step_5');
let vNodes_color_step_6 = Folder2.addColor(Options,'Nodes_color_step_6');
let vNodes_density = Folder2.add(Options,'Nodes_density',0,1,0.001);
Folder2.open();
const Folder3 = gui.addFolder('Edges_configuration');
let vEdges_width = Folder3.add(Options,'Edges_width',0,10,0.1);
let vEdges_length = Folder3.add(Options,'Edges_length',5,500,5);
let vEdges_color = Folder3.addColor(Options,'Edges_color');
let vEdges_style = Folder3.add(Options,'Edges_style',['solid','dashed','dotted']);
let vPaint_layout = Folder3.add(Options,'Paint_layout',['force','circular']);

const Folder4 = gui.addFolder('Nodes & Edges Operations');
const Folder40 = Folder4.addFolder('Nodes_delete');
let vNodes_delete_id = Folder40.add(Options,'Nodes_delete_id').min(0).step(1);
Folder40.add(Options_button,'Nodes_delete_yes_button');
const Folder41 = Folder4.addFolder('Nodes_add');
Folder41.add(Options_button,'Nodes_add_yes_button');
const Folder42 = Folder4.addFolder('Edges_delete');
let vEdges_delete_id1 = Folder42.add(Options,'Edges_delete_id1').min(0).step(1);
let vEdges_delete_id2 = Folder42.add(Options,'Edges_delete_id2').min(0).step(1);
Folder42.add(Options_button,'Edges_delete_yes_button');
const Folder43 = Folder4.addFolder('Edges_add');
let vEdges_add_id1 = Folder43.add(Options,'Edges_add_id1').min(0).step(1);
let vEdges_add_id2 = Folder43.add(Options,'Edges_add_id2').min(0).step(1);
Folder43.add(Options_button,'Edges_add_yes_button');
Folder4.open();




vEdges_width.onChange(function(value){
    nEdges_width = value;
});
vEdges_length.onChange(function(value){
    nEdges_length = value;
});
vEdges_color.onChange(function(value){
    nEdges_color = value;
});
vEdges_style.onChange(function(value){
    nEdges_style = value;
});
vNodes_density.onChange(function(value){
    nNodes_density = value;
});
vNodes_number.onChange(function(value){
    nNodes_number = value;
});
vEdge_probability.onChange(function(value){
    nEdge_probability = value;
});
vInit_edge_number.onChange(function(value){
    nInit_edge_number = value;
});
vRewiring_probability.onChange(function(value){
    nRewiring_probability = value;
});
vInit_node_number.onChange(function(value){
    nInit_node_number = value;
});
vDegree_increase.onChange(function(value){
    nDegree_increase = value;
});
vNodes_size_select.onChange(function(value){
    symbol_size = value;
});
vNodes_color_step_1.onChange(function(value){
    nNodes_color_step_1 = value;
});
vNodes_color_step_2.onChange(function(value){
    nNodes_color_step_2 = value;
});
vNodes_color_step_3.onChange(function(value){
    nNodes_color_step_3 = value;
});
vNodes_color_step_4.onChange(function(value){
    nNodes_color_step_4 = value;
});
vNodes_color_step_5.onChange(function(value){
    nNodes_color_step_5 = value;
});
vNodes_color_step_6.onChange(function(value){
    nNodes_color_step_6 = value;
});
vPaint_layout.onChange(function(value){
    nPaint_layout = value;
});

vSpeed_select.onChange(function(value){
    clearInterval(paint_interval);
    if(value === '0.5'){
        SpeedOfPainting = 2*normolSpeed;
    }
    else if(value === '1.0'){
        SpeedOfPainting = normolSpeed;
    }
    else if(value === '1.5'){
        SpeedOfPainting = Math.floor(normolSpeed/1.5);
    }
    else if(value === '2.0'){
        SpeedOfPainting = Math.floor(normolSpeed/2);
    }
    else if(value === '3.0'){
        SpeedOfPainting = Math.floor(normolSpeed/3);
    }
    else if(value === '5.0'){
        SpeedOfPainting = Math.floor(normolSpeed/5);
    }
    if(click_number % 2 === 1){
        paint_interval = painting();
    }

});

vGeneration_mechanism.onChange(function(value){
    Folder10.close();
    Folder11.close();
    Folder12.close();

    nGeneration_mechanism = value;
    Options.Destruction_mechanism = 'none';
    nDestruction_mechanism = 'none';
    gui.updateDisplay();

    if(value === 'ER_network'){
        Folder10.open();
    }
    else if(value === 'WS_network'){
        Folder11.open();
    }
    else if(value === 'BA_network'){
        Folder12.open();
    }
});

vDestruction_mechanism.onChange(function(value){
    Folder10.close();
    Folder11.close();
    Folder12.close();

    nDestruction_mechanism = value;
    Options.Generation_mechanism = 'none';
    nGeneration_mechanism = 'none';
    gui.updateDisplay();

});

vNodes_delete_id.onChange(function(value){
    nNodes_delete_id = value;
});
vEdges_delete_id1.onChange(function(value){
    nEdges_delete_id1 = value;
});
vEdges_delete_id2.onChange(function(value){
    nEdges_delete_id2 = value;
});
vEdges_add_id1.onChange(function(value){
    nEdges_add_id1 = value;
});
vEdges_add_id2.onChange(function(value){
    nEdges_add_id2 = value;
});

vFrame_select.onChange(function(value){
    nFrame_select = value;
    if(nGeneration_mechanism === 'none'){
        url_operation2();
    }
    else{
        url_operation();
    }
});
