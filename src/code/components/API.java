package code.components;



public class API {
    Server[] servers;
    public API(String...servers){
        this.servers = new Server[servers.length];
        for (int i = 0; i < this.servers.length; i++) {
            this.servers[i] = new Server(s);
        }
    }
}
