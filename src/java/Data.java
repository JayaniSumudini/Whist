
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jay
 */
public class Data {
    
    
static ArrayList<Integer> cards=new ArrayList<>();
static ArrayList<Integer> p1Cards=new ArrayList<>();
static ArrayList<Integer> p2Cards=new ArrayList<>();
static ArrayList<Integer> p3Cards=new ArrayList<>();
static ArrayList<Integer> p4Cards=new ArrayList<>();
static int Trump=0;

//make deal cards among the players
public Data(){
for(int i=0;i<13;i++){
    p1Cards.add(randInt());
    p2Cards.add(randInt());
    p3Cards.add(randInt());
    int r=randInt();
    p4Cards.add(r);
    Trump=r;
}
//System.out.println(p1Cards);
//System.out.println(p2Cards);
//System.out.println(p3Cards);
//System.out.println(p4Cards);
}

//take a card which is not taken
public int randInt(){
Random rand = new Random();
int randNo= rand.nextInt(54);
while(cards.indexOf(randNo) != -1){
randNo= rand.nextInt(54);
}
cards.add(randNo);
return randNo;
}
    
}
