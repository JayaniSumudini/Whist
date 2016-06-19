

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jay
 */
public class State extends HttpServlet {
    
   static  int card_select_times=0;
   static  int card_draw_times=0;
   static int pastTrick=1;
   public static int lead=Play.Trump;
   public static int[] arrayTrump={0,0,0,0};
    public static int[] arrayLead={0,0,0,0};
    public static int[] arrayNoTrump = {0,0,0,0};
    public static int i,maxnum = 1;
    public static int maxIndex = -1;
    

    //handles card selection 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession(true);   
         String username=(String)session.getAttribute( "username"); //identify the username
         int index=Database.players.indexOf(username);
         String card=request.getParameter("card");
         if(Play.currPlayer==index){
             card_select_times+=1;
                switch (index) { //check the player of the selected card
                    case 0:
                        Play.p1Card=card;System.out.println("card:"+Play.p1Card);
                        //Play.p1Cards.remove(Play.p1Cards.indexOf(CardNo(Play.p1Card)));
                        break;
                    case 1:
                        Play.p2Card=card;System.out.println("card:"+Play.p1Card);
                       // Play.p2Cards.remove(Play.p2Cards.indexOf(CardNo(Play.p1Card)));
                        break;
                    case 2:
                        Play.p3Card=card;System.out.println("card:"+Play.p1Card);
                        //Play.p3Cards.remove(Play.p3Cards.indexOf(CardNo(Play.p1Card)));
                        break;
                    case 3:
                        Play.p4Card=card;System.out.println("card:"+Play.p1Card);
                       // Play.p4Cards.remove(Play.p4Cards.indexOf(CardNo(Play.p1Card)));
                        break;
                    default:
                        break;
                }
               Play.trick=((int) card_select_times/4)+1;
               System.out.println("Get a card "+CardNo(card));
               System.out.println(card_select_times+ " "+ Play.trick+" "+pastTrick);
               //adding marks to the winner of the trick
               if(Play.trick !=pastTrick){
                   
                       Play.newTrick=1;
                           System.out.println("ok1");
                           //put card name to int vales
                       int p1=CardNo(Play.p1Card); 
                       int p2=CardNo(Play.p2Card);
                       int p3=CardNo(Play.p3Card); 
                       int p4=CardNo(Play.p4Card); 
                        System.out.println("ok2");
                         //lead card from past winner
                         switch (Play.winner) {
                             case 0:
                                 lead=CardSuit(p1);
                                 break;
                             case 1:
                                 lead=CardSuit(p2);
                                 break;
                             case 2:
                                 lead=CardSuit(p3);
                                 break;
                             case 3:
                                 lead=CardSuit(p4);
                                 break;
                             default:
                                 break;
                         }

                       Play.currPlayer=winnerOfTrick(p1,p2,p3,p4);//select thenew  winner
                       Play.winner=Play.currPlayer;
                       int score;
                       synchronized(Database.scores){
                       score=Database.scores.get( Play.currPlayer); //get the scorses of the winner
                       Database.scores.add (Play.currPlayer, score+1);//add a score to the winner
                       }
                       System.out.println("lead "+lead);
                       System.out.println("trump "+Play.Trump);
                       System.out.println(Play.currPlayer+" "+(score+1));


                        pastTrick+=1;
               }
               else{
                   System.out.println("ok6");
                  Play.newTrick=0;
                  Play.currPlayer=(Play.currPlayer+1)%4;
                  System.out.println(Play.currPlayer);
               }
         }
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
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
    
    public int CardSuit(int cardNo){
    return (cardNo/13);
    }

    //choose the winner using selected cards
 public  int winnerOfTrick(int P1,int P2, int P3, int P4){
int P1_firstNum = calFirstnum(P1) ;
		int P2_firstNum = calFirstnum(P2) ;
		int P3_firstNum = calFirstnum(P3) ;
		int P4_firstNum = calFirstnum(P4) ;
		int P1_secondNum = calSecondnum(P1) ;
		int P2_secondNum = calSecondnum(P2) ;
		int P3_secondNum = calSecondnum(P3) ;
		int P4_secondNum = calSecondnum(P4) ;
		
		//find trump suit cards and lead cards and put into the arrays
		if(P1_firstNum == Play.Trump){
			arrayTrump[0] = P1_secondNum;
		}else if(P1_firstNum == lead){
			arrayLead[0] = P1_secondNum;
		}else{
			arrayNoTrump[0] = P1_secondNum;
		}
		
		if(P2_firstNum == Play.Trump){
			arrayTrump[1] = P2_secondNum;
		}else if(P2_firstNum == lead){
			arrayLead[1] = P2_secondNum;
		}else{
			arrayNoTrump[1] = P2_secondNum;
		}
		
		if(P3_firstNum == Play.Trump){
			arrayTrump[2] = P3_secondNum;
		}else if(P3_firstNum == lead){
			arrayLead[2] = P3_secondNum;
		}else{
			arrayNoTrump[2] = P3_secondNum;
		}
		
		if(P4_firstNum == Play.Trump){
			arrayTrump[3] = P4_secondNum;
		}else if(P4_firstNum == lead){
			arrayLead[3] = P4_secondNum;
		}else{
			arrayNoTrump[3] = P4_secondNum;
		}
		
		for(i = 0; i<=3 ;i++){
			if(arrayTrump[i] >= maxnum){
				maxIndex = i;
				maxnum =arrayTrump[i];
			}
		}
		//compares arrays
		if(maxIndex >=0){
			return maxIndex;
		}else{
			for(i = 0; i<=3 ;i++){
				if(arrayLead[i] >= maxnum){
					maxIndex = i;
					maxnum =arrayLead[i];
				}
			}
			
			if(maxIndex >= 0){
				return maxIndex;
			}else{
				for(i = 0; i<=3 ;i++){
					if(arrayNoTrump[i] >= maxnum){
						maxIndex = i;
						maxnum =arrayNoTrump[i];
					}
				}
			}
			
		}
		
		return maxIndex;
	}
	//card suit
	public static int calFirstnum(int num1){
		return ((int)(num1/13) );
	}
                      //card number of the suit
	public static int calSecondnum(int num2){
		return ((int) ((num2%13)+ 1) );
	}  
    
}
