import de.revollve.lib.config.Translation;

public class TestTranslation {
    public static void main(String[] args) {
        Translation.log_err = true;
        Translation ts = new Translation("test");
        System.out.println(ts.get());

        Translation.language = "german";
        Translation ts2 = new Translation("test2");
        System.out.println(ts2.get());
    }
}
