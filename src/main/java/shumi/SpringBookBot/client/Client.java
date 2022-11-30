package shumi.SpringBookBot.client;

import java.util.ArrayList;

public class Client {
    private long chatID;

    private ArrayList<String> command = new ArrayList<>();

    public Client(long chatID) {this.chatID = chatID;}
    public long getChatID(){ return chatID;}
    public int size() { return command.size();}
    public ArrayList<String> getInformation(int counter){
        int size = size();
        ArrayList<String> result = new ArrayList<>();
        for (int i = size-1; i >= size - counter; i--) {
            result.add(command.get(i));
        }
        return result;
    }
    public void deleteData(int counter) {
        int size = size();
        command.subList(size - counter, size).clear();

    }
    public void addData(String data) {
        command.add(data);
    }
}

