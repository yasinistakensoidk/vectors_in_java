import java.application.Application;
import java.stage.Stage;

public Class Main extends Application {
    public static void main(String[] args){
        launch(args);
        
    }

    @override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Title of the window");
    }
}