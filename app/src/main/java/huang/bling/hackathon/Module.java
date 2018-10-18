package huang.bling.hackathon;

public class Module implements Contract.Module{
    @Override
    public int addNubmer(int num) {
        return num +1;
    }
}
