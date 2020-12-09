package v4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Driver extends Application
{
    // declare widgets
    private TreeView<String> tree;
    private TextField userIDTextArea, groupIDTextArea;
    private Button addUserButton, addGroupButton, openUserViewButton;
    private Button showUserTotalButton, showMessagesTotalButton, showGroupTotalButton, showPositivePercentageButton;
    private Button validateIDsButton, findLastUpdatedUserButton;
    private Label validateIDsLabel, findLastUpdatedUserLabel;
    private TreeItem<String> rootNode, parentNode;
    private Label showUserTotalLabel, showMessagesTotalLabel, showGroupTotalLabel, showPositivePercentageLabel;
    private String selectedNode;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // open control panel
        ControlPanel controlPanel = ControlPanel.getInstance();
        controlPanel.getInstance();

        // initialize root
        rootNode = new TreeItem<String>("Root");
        rootNode.setExpanded(true);

        // initialize tree
        tree = new TreeView<String>(rootNode);
        tree.setShowRoot(true);
        tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) ->
        {
            if(newValue != null)
            {
                selectedNode = newValue.getValue();
            }
        });

        // initialize text fields
        userIDTextArea = new TextField("User ID");
        groupIDTextArea = new TextField("Group ID");

        // initialize buttons
        addUserButton = new Button("Add User");
        addGroupButton = new Button("Add Group");
        openUserViewButton = new Button("Open User View");
        showUserTotalButton = new Button("Show User Total");
        showMessagesTotalButton = new Button("Show Messages Total");
        showGroupTotalButton = new Button("Show Group Total");
        showPositivePercentageButton = new Button("Show Positive Percentage");
        validateIDsButton = new Button("Validate IDs");
        findLastUpdatedUserButton = new Button("Find Latest Updated User");

        // initialize labels
        showUserTotalLabel = new Label();
        showGroupTotalLabel = new Label();
        showMessagesTotalLabel = new Label();
        showPositivePercentageLabel = new Label();
        validateIDsLabel = new Label();
        findLastUpdatedUserLabel = new Label();

        // event listener for add user button
        addUserButton.setOnAction(event ->
        {
            // create new user
            String userID = userIDTextArea.getText();
            parentNode = tree.getSelectionModel().getSelectedItem();
            controlPanel.addUser(userID, parentNode);
        });

        // event listener for add group view button
        addGroupButton.setOnAction(event ->
        {
            // create new user group
            String userGroupID = groupIDTextArea.getText();
            parentNode = tree.getSelectionModel().getSelectedItem();
            controlPanel.addGroup(userGroupID, parentNode);
        });

        // event listener for open user view button
        openUserViewButton.setOnAction(event ->
        {
            // open new user view page
            UserView userView = new UserView(selectedNode);
            User user = controlPanel.getUser(selectedNode);
            userView.start(user);
        });

        // event listener for user total button
        showUserTotalButton.setOnAction(event ->
        {
            Popup.start("User Total", controlPanel.getUserTotal());
            showUserTotalLabel.setText("User Total: " + controlPanel.getUserList().size());
        });

        // event listener for group total button
        showGroupTotalButton.setOnAction(event ->
        {
            Popup.start("Group Total", controlPanel.getGroupTotal());
            showGroupTotalLabel.setText("Group Total: " + controlPanel.getUserGroupList().size());
        });

        // event listener for message total button
        showMessagesTotalButton.setOnAction(event ->
        {
            Popup.start("Messages Total", controlPanel.getMessageTotal());
            showMessagesTotalLabel.setText(controlPanel.getMessageTotal());
        });

        // event listener for positive percentage button
        showPositivePercentageButton.setOnAction(event ->
        {
            Popup.start("Positive Message Percentage", controlPanel.getPositiveMessagePercentage());
            showPositivePercentageLabel.setText(controlPanel.getPositiveMessagePercentage());
        });

        // event listener for validate id button
        validateIDsButton.setOnAction(event ->
        {
            Popup.start("ID Validation", controlPanel.getIDValidation());
            validateIDsLabel.setText(controlPanel.getIDValidation());
        });

        // event listener for find last updated user button
        findLastUpdatedUserButton.setOnAction(event ->
        {
            Popup.start("Last Updated User", controlPanel.getLastUpdatedUser());
            findLastUpdatedUserLabel.setText(controlPanel.getLastUpdatedUser());
        });

        // format layout
        VBox vbox1 = new VBox(tree);
        HBox hbox1 = new HBox(10, userIDTextArea, addUserButton);
        HBox hbox2 = new HBox(10, groupIDTextArea, addGroupButton);

        GridPane pane1 = new GridPane();
        pane1.add(showUserTotalButton, 0, 0);
        pane1.add(showGroupTotalButton, 1, 0);
        pane1.add(showUserTotalLabel, 0, 1);
        pane1.add(showGroupTotalLabel, 1, 1);
        pane1.add(showMessagesTotalButton, 0, 2);
        pane1.add(showPositivePercentageButton, 1, 2);
        pane1.add(showMessagesTotalLabel, 0, 3);
        pane1.add(showPositivePercentageLabel, 1, 3);
        pane1.add(validateIDsButton, 0, 4);
        pane1.add(findLastUpdatedUserButton, 1, 4);
        pane1.add(validateIDsLabel, 0, 5);
        pane1.add(findLastUpdatedUserLabel, 1, 5);
        pane1.setVgap(10);
        pane1.setHgap(10);

        VBox vbox2 = new VBox(20, hbox1, hbox2, openUserViewButton, pane1);
        HBox hbox4 = new HBox(20, vbox1, vbox2);

        // create scene
        Scene scene = new Scene(hbox4, 650, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Twitter Control Panel");
        primaryStage.show();
    }
}