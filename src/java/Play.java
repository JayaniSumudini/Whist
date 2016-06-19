
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jay
 */
public class Play extends HttpServlet {  
    
    static int  wait=0; //waiting for players
    static int  deal=0; // the dealing number
    static int  trick=1; //trick number 1-13
    static int draw=0; // draw time 1-4
    static ArrayList<Integer> cards=new ArrayList<>();
    static ArrayList<Integer> p1Cards=new ArrayList<>();
    static ArrayList<Integer> p2Cards=new ArrayList<>();
    static ArrayList<Integer> p3Cards=new ArrayList<>();
    static ArrayList<Integer> p4Cards=new ArrayList<>();
    static int Trump=0;
    static String p1Card=null;
    static String p2Card=null;
    static String p3Card=null;
    static String p4Card=null;
    static int currPlayer=0;
    static int newTrick=0;
    static int winner=0;
    public Play(){
    Data();
    }
    
    //waiting msg 
    public JSONObject waitMsg(int i){
    JSONArray a=new JSONArray();
    JSONObject js=new JSONObject();
    js.put("showHand", false);
    js.put("showCards", false);
    js.put("message", "Waiting for others to connect. Only "+i+" player/s connected");
    return js;
    }
    
    //winner msg
    public JSONObject WinnerMsg(){
    JSONArray a=new JSONArray();
    JSONObject js=new JSONObject();
    js.put("showHand", false);
    js.put("showCards", false);
    js.put("message", "hurray you are the winner!!!");
    return js;
    }
    
    //lost msg
    public JSONObject LostMsg(){
    JSONArray a=new JSONArray();
    JSONObject js=new JSONObject();
    js.put("showHand", false);
    js.put("showCards", false);
    js.put("message", "you are lost. Try again!");
    return js;
    }
    
    //dealing cards
    public JSONObject Dealing(int index){
     JSONArray a=new JSONArray(); //cards array
     for(int i=0;i<13;i++){
         JSONObject image=new JSONObject();
         switch (index) {
             case 0:
                 image.put( "image","cards/"+(int)p1Cards.get(i)/13+"_"+(((int)p1Cards.get(i)%13)+1)+".png");
                 break;
             case 1:
                 image.put( "image","cards/"+(int)p2Cards.get(i)/13+"_"+(((int)p2Cards.get(i)%13)+1)+".png");
                 break;
             case 2:
                 image.put( "image","cards/"+(int)p3Cards.get(i)/13+"_"+(((int)p3Cards.get(i)%13)+1)+".png");
                 break;
             case 3:
                 image.put( "image","cards/"+(int)p4Cards.get(i)/13+"_"+(((int)p4Cards.get(i)%13)+1)+".png");
                 break;
             default:
                 break;
         }
         a.put(image);
     }
    JSONObject js=new JSONObject();
    js.put("cards", a);
    js.put("showHand", true);
    js.put("showCards", true);
   if(index==0) {
       js.put("message", "Trump card: "+TrumpCard(Trump)+". Your turn");
   }
   else  js.put("message", "Trump card: "+TrumpCard(Trump)+". Wait for others play their cards");
   // System.out.println(js.toString());
    return js;
    }
    
    //when playing cards
    public JSONObject ShowCards(int index){
     JSONArray a=new JSONArray();
         for(int i=0;i<13;i++){
         JSONObject image=new JSONObject();
         switch (index) {
             case 0:
                 try{
                 image.put( "image","cards/"+(int)p1Cards.get(i)/13+"_"+(((int)p1Cards.get(i)%13)+1)+".png");
                 }catch(Exception e){System.out.println("err");}
                 break;
             case 1:
                 try{
                 image.put( "image","cards/"+(int)p2Cards.get(i)/13+"_"+(((int)p2Cards.get(i)%13)+1)+".png");
                 }catch(Exception e){System.out.println("err");}
                 break;
             case 2:
                 try{
                 image.put( "image","cards/"+(int)p3Cards.get(i)/13+"_"+(((int)p3Cards.get(i)%13)+1)+".png");
                 }catch(Exception e){System.out.println("err");}
                 break;
             case 3:
                 try{
                 image.put( "image","cards/"+(int)p4Cards.get(i)/13+"_"+(((int)p4Cards.get(i)%13)+1)+".png");
                 }catch(Exception e){System.out.println("err");}
                 break;
             default:
                 break;
         }
         a.put(image);
     }
     
    JSONObject js=new JSONObject();
    js.put("cards", a);
   if(p1Card!=null) js.put("card1", p1Card);
    if(p2Card!=null)js.put("card2", p2Card);
    if(p3Card!=null) js.put("card3", p3Card);
    if(p4Card!=null) js.put("mycard", p4Card);
    js.put("showHand", true);
    js.put("showCards", true);
    
           if(index==currPlayer) {
               js.put("message", "Trump card: "+TrumpCard(Trump)+". Your turn");
           }
           else  js.put("message", "Trump card: "+TrumpCard(Trump)+". Wait for others play their cards");
   //System.out.println(js.toString());
    return js;
    }
    
    //when a new trick started
    public JSONObject NewTrick(int index){
     JSONArray a=new JSONArray();
     
//         Play.p1Cards.remove(Play.p1Cards.indexOf(CardNo(Play.p1Card)));
//         Play.p2Cards.remove(Play.p2Cards.indexOf(CardNo(Play.p2Card)));
//         Play.p3Cards.remove(Play.p3Cards.indexOf(CardNo(Play.p3Card)));
//         Play.p4Cards.remove(Play.p4Cards.indexOf(CardNo(Play.p4Card)));
     
         for(int i=0;i<13-trick;i++){
         JSONObject image=new JSONObject();
         System.out.println("p1:"+CardNo(p1Card)+" "+p1Cards);
         System.out.println("p2:"+CardNo(p2Card)+" "+p2Cards);
        
         switch (index) {
             case 0:
                 try{
                 image.put( "image","cards/"+(int)p1Cards.get(i)/13+"_"+(((int)p1Cards.get(i)%13)+1)+".png");
                  a.put(image);
                 }catch(Exception e){System.out.println("err");}
                 break;
             case 1:
                 try{
                 image.put( "image","cards/"+(int)p2Cards.get(i)/13+"_"+(((int)p2Cards.get(i)%13)+1)+".png");
                  a.put(image);
                 }catch(Exception e){System.out.println("err");}
                 break;
             case 2:
                 try{
                 image.put( "image","cards/"+(int)p3Cards.get(i)/13+"_"+(((int)p3Cards.get(i)%13)+1)+".png");
                  a.put(image);
                 }catch(Exception e){System.out.println("err");}
                 break;
             case 3:
                 try{
                 image.put( "image","cards/"+(int)p4Cards.get(i)/13+"_"+(((int)p4Cards.get(i)%13)+1)+".png");
                  a.put(image);
                 }catch(Exception e){System.out.println("err");}
                 break;
             default:
                 break;
         }
     }
     p1Card=null;
    p2Card=null;
    p3Card=null;
    p4Card=null;
    JSONObject js=new JSONObject();
    js.put("cards", a);
    js.put("showHand", true);
    js.put("showCards", true);
    
           if(index==currPlayer) {
               js.put("message", "Trump card: "+TrumpCard(Trump)+"New Trick . Your turn");
           }
           else  js.put("message", "Trump card: "+TrumpCard(Trump)+"New Trick . Wait for others play their cards");
   //System.out.println(js.toString());
    return js;
    }

    //handle waiting state
    protected void WAIT(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {   
           if(Database.players.size()==1) {out.println(waitMsg(1));}
           if(Database.players.size()==2) {out.println(waitMsg(2));}
           if(Database.players.size()==3) {out.println(waitMsg(3));}
        }
        
    }
    //handle dealing state
     protected void DEAL(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          deal=1;
          HttpSession session = request.getSession(true);   
          String username=(String)session.getAttribute( "username"); //identify the username
        //  System.out.println("index:"+Database.players.indexOf(username) +username);
          out.println(Dealing(Database.players.indexOf(username)));
        }
        
    }
     //handle show state
      protected void SHOW(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          deal=1;
          HttpSession session = request.getSession(true);   
          String username=(String)session.getAttribute( "username"); //identify the username
         // System.out.println("show");
         if(trick<=13)out.println(ShowCards(Database.players.indexOf(username)));
         else{
             if(GetMax()==Database.players.indexOf(username)){
                     out.println(WinnerMsg());
             }else{
                    out.println(LostMsg());
             }
         }
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        if(deal==0 && wait==0) WAIT(request, response); //waiting state
        if(wait==1) {
            if(trick==0)  DEAL(request, response); //dealing state
            else{
              SHOW(request,response);//palying state      
            }
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }
  
    //shuffle cards among the players
public void Data(){
for(int i=0;i<13;i++){
    p1Cards.add(randInt());
    p2Cards.add(randInt());
    p3Cards.add(randInt());
    int r=randInt();
    p4Cards.add(r);
    Trump=r/13;
}
//System.out.println(p1Cards);
//System.out.println(p2Cards);
//System.out.println(p3Cards);
//System.out.println(p4Cards);
}

//random cards
    public int randInt(){
    Random rand = new Random();
    int randNo= rand.nextInt(52);
    while(cards.indexOf(randNo) != -1){
    randNo= rand.nextInt(52);
    }
    cards.add(randNo);
    return randNo;
    }

    //get card number using card parameter
    public int CardNo(String cardName){
       String suite=cardName.substring(6, 7);
        String n1;
        if(cardName.charAt(9)=='.'){
            n1=cardName.substring(8, 9);
        }
        else{
            n1=cardName.substring(8, 10);
        }
        return ( Integer.parseInt(suite)*13) + ( Integer.parseInt(n1))-1;
    }
    
    //select trump card name
    public String TrumpCard(int cardName){
       int suite=(int)cardName/13;
        switch (suite) {
            case 0:
                return "diamonds";
            case 1:
                return "clubs";
            case 2:
                return "hearts";
            default:
                return "spades";
        }
    }
   
    //get the max score 
    public int GetMax(){
        int min=0;
        int index=0;
        for(int i=0;i<4;i++){
            if(Database.scores.get(i)>min) {
            min=Database.scores.get(i);
            index=i;
            }
        }
        
        return index;
    }
    
}
