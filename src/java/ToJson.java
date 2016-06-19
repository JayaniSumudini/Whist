
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author jay
 */


public class ToJson {
    
    String json="{\"cards\":[{\"image\": \"cards/1_8.png\" },{\"image\": \"cards/2_11.png\"},{\"image\": \"cards/3_10.png\"},{\"image\": \"cards/1_5.png\"},{\"image\": \"cards/1_8.png\"},{\"image\": \"cards/3_12.png\"},{\"image\": \"cards/3_13.png\"}] , \"card1\":\"cards/3_4.png\" , \"card2\":\"cards/3_1.png\",\"showHand\" : true,\"showCards\" : true ,\"message\" : \"Play your card\"}";
 String wait4 ="{\"cards\":[],\"showHand\" : false, \"showCards\" : false , \"message\" : \"lets play  dal\"}";
   
    public ToJson(){
        Data d=new Data();
    }
    
    public JSONObject makeDeal(){
    JSONObject dealJson=new JSONObject();
    JSONArray cards=new JSONArray();
            for(int i=0;i<3;i++){
                cards.put("image: cards/"+ (int)((Data.p1Cards.get(i))/13)+ "_"+ (Data.p1Cards.get(i))%13 +".png" );
            }
      dealJson.put("cards",cards);
      dealJson.put("card1","image\":\"cards/1_1.png");
      dealJson.put("card2","image\":\"cards/2_1.png");
      dealJson.put("card3","image\":\"cards/3_1.png");
      dealJson.put("mycard","image\":\"cards/4_1.png");
      dealJson.put("showHand",true);
      dealJson.put("showCards",true);
      dealJson.put("message","your turn");
      System.out.println(dealJson);
     return dealJson;  
    }
    
    public JSONObject waitMsg(int i){
    JSONObject dealJson=new JSONObject();
    JSONArray cards=new JSONArray();
      dealJson.put("cards","[]");
      dealJson.put("showHand",false);
      dealJson.put("showCards",false);
      dealJson.put("message","Waiting for others to connect. Only  "+i+" players connected");
     return dealJson;  
    }
    
    public static void main(String [] args){
        ToJson s=new ToJson();
        s.makeDeal();
    }
}
