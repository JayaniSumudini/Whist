import java.io.*;
import java.util.*;

public class winner {
	public static int Trump,lead;
	public static int[] array={0,0,0,0};
	public static int[] arrayNoTrump = {0,0,0,0};
	public static int i,maxnum = 1;
	public static int maxIndex = -1;
	
	public static void main (String args []){
            String c="abc2w";
            System.out.println(c.substring(3,4));
        
        }
        public int CardNo(String cardName){
       String suite=cardName.substring(6, 6);
        String n1;
        if(cardName.charAt(9)=='.'){
            n1=cardName.substring(8, 8);
        }
        else{
            n1=cardName.substring(8, 9);
        }
        return ( Integer.parseInt(suite)*13) + ( Integer.parseInt(n1))-1;
    }
	
	public winner(int T){
		Trump = T;
	}
	
	public  int winnerOfTrick(int P1,int P2, int P3, int P4){
		int P1_firstNum = calFirstnum(P1) ;
		int P2_firstNum = calFirstnum(P2) ;
		int P3_firstNum = calFirstnum(P3) ;
		int P4_firstNum = calFirstnum(P4) ;
		int P1_secondNum = calSecondnum(P1) ;
		int P2_secondNum = calSecondnum(P2) ;
		int P3_secondNum = calSecondnum(P3) ;
		int P4_secondNum = calSecondnum(P4) ;
		
		if(P1_firstNum == Trump){
			array[0] = P1_secondNum;
		}else{
			arrayNoTrump[0] = P1_secondNum;
		}
		
		if(P2_firstNum == Trump){
			array[1] = P2_secondNum;
		}else{
			arrayNoTrump[1] = P2_secondNum;
		}
		
		if(P3_firstNum == Trump){
			array[2] = P3_secondNum;
		}else{
			arrayNoTrump[2] = P3_secondNum;
		}
		
		if(P4_firstNum == Trump){
			array[3] = P4_secondNum;
		}else{
			arrayNoTrump[3] = P4_secondNum;
		}
		
		for(i = 0; i<=3 ;i++){
			if(array[i] >= maxnum){
				maxIndex = i;
				maxnum =array[i];
			}
		
		}
		
		if(maxIndex == 0){
			lead = P1 ;
		}else if(maxIndex == 1){
			lead = P2;
		}else if(maxIndex == 2){
			lead = P3;
		}else if(maxIndex == 3){
			lead = P4;
		}else{
			for(i = 0; i<=3 ;i++){
				if(arrayNoTrump[i] >= maxnum){
					maxIndex = i;
					maxnum =arrayNoTrump[i];
				}
				
				if(maxIndex == 0){
					lead = P1 ;
				}else if(maxIndex == 1){
					lead = P2;
				}else if(maxIndex == 2){
					lead = P3;
				}else{
					lead = P4;
				}
			
			}
			
		}
		return maxIndex;
	}
	
	public static int calFirstnum(int num1){
		return ((int)(num1/13) );
	}
	public static int calSecondnum(int num2){
		return ((int) ((num2%13)+ 1) );
	}
	
	
}