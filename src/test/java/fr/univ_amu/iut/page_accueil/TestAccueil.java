package fr.univ_amu.iut.page_accueil;

import fr.univ_amu.iut.HelloApplication;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class TestAccueil extends ApplicationTest {
    Stage stage;

    @Start
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
            TestAccueil.this.stage = new Stage();
            try {
                FxToolkit.setupStage((sta) -> {
                    try {
                        new HelloApplication().start(TestAccueil.this.stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
    }

    @AfterEach
    void afterEachTest(FxRobot robot) throws TimeoutException {
        FxToolkit.cleanupStages();
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }
    //@Disabled
    @Test
    void should_initialize_stage_is_showing() {
        assertThat(stage.isShowing()).isTrue();
    }

    //@Disabled
    @Test
    void should_initialize_stage_with_correct_title() {
        assertThat(stage.getTitle()).isEqualTo("Dico Pédago");
    }

    //@Disabled
    @Test
    public void should_contain_button_with_label_acceder() {
        verifyThat("#labelBoutonAccederAccueil", hasText("Accéder"));
    }

    //@Disabled
    @Test
    public void should_contain_button_with_text_se_connecter() {
        verifyThat("#boutonSeConnecterAccueil", hasText("Se connecter"));
    }

    @Test
    public void button_se_connecter_should_redirect_to_connexion_administrateur() {
        clickOn("#boutonSeConnecterAccueil");
        assertThat(clickOn("#boutonSeConnecterAccueil").equals(new FxRobotException("")));//On vérifie qu'on est plus sur la page accueil'
    }
}
