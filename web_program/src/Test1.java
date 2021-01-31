import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

@WebServlet("/Test1")
public class Test1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws  ServletException,IOException{
        doPost(req,resp);
    }
        //服务器的主体线程
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        String methodName = req.getParameter("type");
        if(methodName == null || methodName.isEmpty()){
            throw new RuntimeException("no parameter");
        }
        //通过方法名直接调用所需的类
        /*Class c = this.getClass();
        Method method = null;
        try{
            method = c.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        }catch (Exception e){
            throw new RuntimeException("no method");
        }
        try{
            method.invoke(this,req,resp);
        }catch (Exception e){
            System.out.println("the method is wrong");
        }
        */
        //对方法名进行选择出所需要的方法
        switch (methodName){
            case "ER_network":
                ER_network(req,resp);
                break;
            case "WS_network":
                WS_network(req,resp);
                break;
            case "BA_network":
                BA_network(req,resp);
                break;
            case "Degree_centrality":
                Degree_centrality(req,resp);
                break;
            case "betweenness_centrality":
                Betweeness_centrality(req,resp);
                break;
            case "Closeness_centrality":
                Closeness_centrality(req,resp);
                break;
            case "eigenvector_centrality":
                eigenvector_centrality(req,resp);
                break;
            case "Katz_centrality":
                Katz_centrality(req,resp);
                break;
            case "PageRank":
                PageRank(req,resp);
                break;
            case "Nodes_delete":
                Nodes_delete(req,resp);
                break;
            case "Nodes_add":
                Nodes_add(req,resp);
                break;
            case "Edges_delete":
                Edges_delete(req,resp);
                break;
            case "Edges_add":
                Edges_add(req,resp);
                break;
            case "filereader":
                readFile(req,resp);
                break;
            default:
                System.out.println("wrone");
        }
    }
    private void ER_network(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session = req.getSession();
        boolean test = true;
        String backjson = null;
        resp.setContentType("text/html;charset=utf8");
        //获取前段传来的参数
        String frame = req.getParameter("frame");
        String para0 = req.getParameter("para0");
        String para1 = req.getParameter("para1");
        int fr = Integer.parseInt(frame);
        int num = Integer.parseInt(para0);
        Double p = Double.parseDouble(para1);
        //判断储存在session中的数据是否需要重新生成网络
        if (session.getAttribute("method")  != null){
            if(session.getAttribute("method").toString().equals("ER_network")){
                if((int)session.getAttribute("para0") == num) {
                    test = false;
                }
            }
        }
        dealDatabase hhh = new dealDatabase();
        try {
            if (test) {
                webconstruct a = new webconstruct(num);
                a.ERWebconfig(p);
                session.setAttribute("graph", a.gragh);
                session.setAttribute("method", "ER_network");
                session.setAttribute("para0",num);
            }
        }catch (Exception e){

        }
        //将数据库中的内容转换为json格式
        if(fr < hhh.getMaxFrame(1)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(1);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
        out.close();
    }
    private void WS_network(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session = req.getSession();
        boolean test = true;
        String backjson = null;
        resp.setContentType("text/html;charset=utf8");
        String frame = req.getParameter("frame");
        String para0 = req.getParameter("para0");
        String para1 = req.getParameter("para1");
        String para2 = req.getParameter("para2");

        int fr = Integer.parseInt(frame);
        int num = Integer.parseInt(para0);
        int n = Integer.parseInt(para1);
        Double p = Double.parseDouble(para2);
        if (session.getAttribute("method")  != null){
            if(session.getAttribute("method").toString().equals("WS_network")){
                if((int)session.getAttribute("para0") == num) {
                    test = false;
                }
            }
        }
        dealDatabase hhh = new dealDatabase();
        try {
            if (test) {
                webconstruct a = new webconstruct(num);
                a.WSWebConfig(n, p);
                session.setAttribute("graph", a.gragh);
                session.setAttribute("method", "WS_network");
                session.setAttribute("para0",num);
            }
        }catch (Exception e){

        }
        if(fr < hhh.getMaxFrame(1)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(1);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
    }
    private void BA_network(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session = req.getSession();
        boolean test = true;
        String backjson = null;
        resp.setContentType("text/html;charset=utf8");
        String frame = req.getParameter("frame");
        String para0 = req.getParameter("para0");
        String para1 = req.getParameter("para1");
        String para2 = req.getParameter("para2");
        int fr = Integer.parseInt(frame);
        int num = Integer.parseInt(para0);
        int add = Integer.parseInt(para1);
        int ad = Integer.parseInt(para2);
        if (session.getAttribute("method")  != null){
            if(session.getAttribute("method").toString().equals("BA_network")){
                if ((int)session.getAttribute("para0") == num) {
                    test = false;
                }
            }
        }
        dealDatabase hhh = new dealDatabase();

        try {
            if (test) {
                webconstruct a = new webconstruct(num);
                a.BAWebConfig(add, num-add, ad);
                session.setAttribute("graph", a.gragh);
                session.setAttribute("method", "BA_network");
                session.setAttribute("para0",num);
            }
        }catch (Exception e){

        }
        if(fr < hhh.getMaxFrame(1)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(1);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
        out.close();
    }
    private void Degree_centrality(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{

        HttpSession session= req.getSession();
        boolean judge = true;
        String frame = req.getParameter("frame");
        String backjson = null;
        int fr = Integer.parseInt(frame);
        if(session.getAttribute("method") != null) {
            if (session.getAttribute("method").toString().equals("degree")) {
                judge = false;
            }
        }
        dealDatabase hhh = new dealDatabase();
        try {
            if (judge) {
                session.setAttribute("method", "degree");
                Gragh hh = (Gragh) session.getAttribute("graph");
                DegreeCentrality test = new DegreeCentrality(hh);
                test.dismiss();
            }
        }catch (Exception e){
        }
        if(fr < hhh.getMaxFrame(2)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(2);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
        out.close();
    }
    private void Betweeness_centrality(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        boolean judge = true;
        String frame = req.getParameter("frame");
        String backjson = null;
        int fr = Integer.parseInt(frame);
        if(session == null){
            System.out.println("wrong");
            return;
        }
        if(session.getAttribute("method") != null) {
            if (session.getAttribute("method").toString().equals("between")) {
                judge = false;
            }
        }
        dealDatabase hhh = new dealDatabase();
        try {
            if (judge) {
                Gragh hh = (Gragh) session.getAttribute("graph");
                BetweennessCentrality test = new BetweennessCentrality(hh);
                test.dismiss();
                session.setAttribute("method", "between");
            }
        }catch (Exception e){
        }
        if(fr < hhh.getMaxFrame(2)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(2);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
        out.close();
    }
    private void Closeness_centrality(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        String frame = req.getParameter("frame");
        String backjson = null;
        boolean judge = true;
        int fr = Integer.parseInt(frame);
        if(session == null){
            System.out.println("wrong");
            return;
        }
        if(session.getAttribute("method") != null) {
            if (session.getAttribute("method").toString().equals("closeness")) {
                judge = false;
            }
        }
        dealDatabase hhh = new dealDatabase();
        try {
            if (judge) {
                Gragh hh = (Gragh) session.getAttribute("graph");
                ClosenessCentrality test = new ClosenessCentrality(hh);
                test.dismiss();
                session.setAttribute("method", "closeness");
            }
        }catch (Exception e){
        }
        if(fr < hhh.getMaxFrame(2)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(2);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
        out.close();
    }
    private void eigenvector_centrality(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        String frame = req.getParameter("frame");
        String backjson = null;
        Boolean judge = true;
        int fr = Integer.parseInt(frame);
        if(session == null){
            System.out.println("wrong");
            return;
        }
        if(session.getAttribute("method") != null) {
            if (session.getAttribute("method").toString().equals("eigenvector")) {
                judge = false;
            }
        }
        dealDatabase hhh = new dealDatabase();
        try {
            if (judge) {
                Gragh hh = (Gragh) session.getAttribute("graph");
                EigenvectorCentrality test = new EigenvectorCentrality(hh);
                test.dismiss();
                session.setAttribute("method", "eigenvector");
            }
        }catch (Exception e){
        }
        if(fr < hhh.getMaxFrame(2)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(2);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
        out.close();
    }
    private void Katz_centrality(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        String frame = req.getParameter("frame");
        String backjson = null;
        boolean judge = true;
        int fr = Integer.parseInt(frame);
        if(session == null){
            System.out.println("wrong");
            return;
        }
        if(session.getAttribute("method") != null) {
            if (session.getAttribute("method").toString().equals("katz")) {
                judge = false;
            }
        }
        dealDatabase hhh = new dealDatabase();
        try {
            if (judge) {
                Gragh hh = (Gragh) session.getAttribute("graph");
                katzCentrality test = new katzCentrality(hh);
                test.dismiss();
                session.setAttribute("method", "katz");
            }
        }catch (Exception e){
        }
        if(fr < hhh.getMaxFrame(2)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(2);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
        out.close();
    }
    private void PageRank(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        String frame = req.getParameter("frame");
        String backjson = null;
        boolean judge = true;
        int fr = Integer.parseInt(frame);
        if(session == null){
            System.out.println("wrong");
            return;
        }
        if(session.getAttribute("method") != null) {
            if (session.getAttribute("method").toString().equals("pagerank")) {
                judge = false;
            }
        }
        dealDatabase hhh = new dealDatabase();
        try {
            if (judge) {
                Gragh hh = (Gragh) session.getAttribute("graph");
                PagerankCentrality test = new PagerankCentrality(hh);
                test.dismiss();
                session.setAttribute("method", "pagerank");
            }
        }catch (Exception e){
        }
        if(fr < hhh.getMaxFrame(2)) {
            Trans trans = hhh.getTrans(fr);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }else {
            Trans trans = hhh.getTrans2(2);
            Gson gson = new Gson();
            backjson = gson.toJson(trans);
        }
        PrintWriter out = resp.getWriter();
        out.write(backjson);
        out.close();
    }
    private void Nodes_delete(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        dealDatabase hhh = new dealDatabase();
        String backjson = null;
        String para0 = req.getParameter("para0");
        int noteid = Integer.parseInt(para0);
        if(session == null){
            return;
        }
        try {
            Gragh hh = (Gragh) session.getAttribute("graph");
            hh.delselnote(noteid);
            session.setAttribute("graph", hh);
        }catch (Exception e){

        }
        Trans trans = hhh.getTrans2(1);
        Gson gson = new Gson();
        backjson = gson.toJson(trans);
        PrintWriter out = resp.getWriter();
        out.println(backjson);
        out.close();
    }
    private void Edges_delete(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        dealDatabase hhh = new dealDatabase();
        String backjson = null;
        String para0 = req.getParameter("para0");
        String para1 = req.getParameter("para1");
        int noteid1 = Integer.parseInt(para0);
        int noteid2 = Integer.parseInt(para1);
        if(session == null){
            return;
        }
        try {
            Gragh hh = (Gragh) session.getAttribute("graph");
            hh.delselside(noteid1, noteid2);
            session.setAttribute("graph", hh);
        }catch (Exception e){

        }
        Trans trans = hhh.getTrans2(1);
        Gson gson = new Gson();
        backjson = gson.toJson(trans);
        PrintWriter out = resp.getWriter();
        out.println(backjson);
        out.close();
    }
    private void Nodes_add(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        dealDatabase hhh = new dealDatabase();
        String backjson = null;
        String para0 = req.getParameter("para0");
        int noteid = Integer.parseInt(para0);
        if(session == null){
            return;
        }

        try {
            Gragh hh = (Gragh) session.getAttribute("graph");
            hh.addselnote(noteid);
            session.setAttribute("graph", hh);
        }catch (Exception e){

        }
        Trans trans = hhh.getTrans2(1);
        Gson gson = new Gson();
        backjson = gson.toJson(trans);
        PrintWriter out = resp.getWriter();
        out.println(backjson);
        out.close();
    }
    private void Edges_add(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        HttpSession session= req.getSession();
        dealDatabase hhh = new dealDatabase();
        String backjson = null;
        String para0 = req.getParameter("para0");
        String para1 = req.getParameter("para1");
        int noteid1 = Integer.parseInt(para0);
        int noteid2 = Integer.parseInt(para1);
        if(session == null){
            return;
        }
        Gragh hh = (Gragh) session.getAttribute("graph");
        hh.addselside(noteid1,noteid2);
        session.setAttribute("graph",hh);
        Trans trans = hhh.getTrans2(1);
        Gson gson = new Gson();
        backjson = gson.toJson(trans);
        PrintWriter out = resp.getWriter();
        out.println(backjson);
        out.close();
    }
    public void readFile(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        String into = req.getParameter("para0");
        HttpSession session = req.getSession();
        int counter = 1;
        int remarks = 1;
        String backjson = null;
        dealDatabase hhh = new dealDatabase();
        hhh.clearDatabase();
        int[] ss = hhh.StringtoInt(into);
        Gragh gragh = new Gragh(ss[0]);
        for (int i = 1;i < ss.length;i = i+2){
            gragh.addOneSide(ss[i],ss[i+1]);
        }
        session.setAttribute("graph",gragh);
        gragh.setClusteringCoefficient();
        gragh.setDegreeDistribution();
        gragh.setAverageRoute();
        gragh.setNotenumber();
        hhh.addOnestep(counter, gragh.arrayoutMatrix(), gragh.arrayOutNote(), remarks, gragh.getDegreeDistribution(), gragh.getAverageRoute(), gragh.getClusteringCoefficient(), gragh.getNotenumber(), gragh.getSideNumber());
        Trans trans = hhh.getTrans2(1);
        Gson gson = new Gson();
        backjson = gson.toJson(trans);
        PrintWriter out = resp.getWriter();
        out.println(backjson);
        out.close();
    }
}
