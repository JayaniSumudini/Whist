

/**
 *
 * @author jay
 */
public enum Status {
    
    Waiting("waiting"),
    Yourturn("your turn"),
    othersturn("wait for others to play"),
    Winner("hurray you are the winner!!!"),
    lost("you are lost. Try again!"),
    Error("Try another card");
    
    private final String status;
    
    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    
}
