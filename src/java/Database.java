/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jay
 */
public class Database extends HttpServlet {

 static ArrayList<String> players=new ArrayList<>();
 static ArrayList<Integer> scores=new ArrayList<>(); 
  
  //this is register players for the game
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String state=request.getParameter("state");
            if(state.equals("register")==true){  
                HttpSession session = request.getSession(true);   
                //only 4 players can play -> register
                if(players.size()<4){
                synchronized(players){
                players.add((String)session.getAttribute( "username" ));
                scores.add(0);
                System.out.println((String)session.getAttribute( "username" ));
                }
                }
            }
        }
    }
    
     // this shows number of players and their scores
    protected void processLog(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           HttpSession session = request.getSession(true); 
           
               try{
                  if(players.size()<4){ //when players  are waiting
                      int i=0;
                      Iterator itr=players.iterator();
                      Iterator itr1=scores.iterator();
                       while(itr.hasNext()){
                        out.println("<h4>Player "+(i+1)+" :"+itr.next());
                        out.println("Score   :"+itr1.next()+"</h4>");
                        i++;
                       }
                  }
                  else{ //when players are playing the game
                       Play.wait=1;
                       int i=0;
                      Iterator itr=players.iterator();
                      Iterator itr1=scores.iterator();
                       while(itr.hasNext()){
                        out.println("<h4>Player "+(i+1)+" :"+itr.next());
                        out.println("Score   :"+itr1.next()+"</h4>");
                        i++;
                       }
                  }
               }
               finally{
                   out.close();
            
            }
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processLog(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
