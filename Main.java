import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

/***
 * This class is used to set up the scenes used in javaFX for the GUI
 */

public class Main extends Application {
    Stage window;
    Scene login, mainMenu, createAccount, propertyTaxPaymentDataProperty, propertyTaxPaymentDataOwner, propertyTaxStatistics,
            overduePropertyTax, balanceStatementForm, registerProperty, makePayment, viewBalanceStatement, changePassword,
            viewProperties, adminMenu, genericAdminViewData;
    TaxManagementSystem tms;

    /***
     * The main starts the Program and launches the GUI
     */

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-c")) {
            CmdLineInterface cmdLine = new CmdLineInterface();
            cmdLine.run();
        } else {
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        tms = new TaxManagementSystem();
        window = primaryStage;

        /***
         * Sets up the grid layout and sets the padding distance from the edge of the GUI
         */
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Creates the initial text present on the login scene as well as the buttons.
         * It also adds the text, textfields and buttons to the grid
         */
        Text welcomeTxt = new Text("Login:");
        welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
        grid.add(welcomeTxt, 0, 0);

        Label usernameText = new Label("Username:");
        grid.add(usernameText, 0, 1);

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("username");
        grid.add(usernameInput, 1, 1);

        Label passwordText = new Label("Password:");
        grid.add(passwordText, 0, 2);

        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("password");
        grid.add(passwordInput, 1, 2);

        Label invalidLoginText = new Label("Invalid username or password");
        grid.add(invalidLoginText, 1, 5);
        invalidLoginText.setVisible(false);

        Label createAccountText = new Label("Not registered?");
        grid.add(createAccountText, 0, 33);

        Button createAccountButton = new Button("Create Account");
        grid.add(createAccountButton, 1, 33);

        /***
         * Sets the login scene its dimensions and its grid
         */
        login = new Scene(grid, 500, 500);

        Button loginButton = new Button("login");
        grid.add(loginButton, 1, 3);


        //MainMenu

        /***
         *Creates a new grid for the mainMenu scene
         */
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.TOP_CENTER);
        grid2.setVgap(10);
        grid2.setHgap(10);
        grid2.setPadding(new Insets(10, 10, 10, 10));

        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */
        Text menuText = new Text("Main Menu");
        menuText.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        grid.add(menuText, 0, 0);


        Button registerPropertyButton = new Button("Register Property");
        grid.add(registerPropertyButton, 0, 1);

        Button viewPropertiesButton = new Button("View Properties");
        grid.add(viewPropertiesButton, 0, 2);

        Button balanceStatementsButton = new Button("Balance Statements");
        grid.add(balanceStatementsButton, 0, 3);

        Button changePasswordButton = new Button("Change Password");
        grid.add(changePasswordButton, 0, 5);

        Button logoutButton = new Button("log out");
        grid.add(logoutButton, 0, 28);


        Button payPropertyTax = new Button("Pay Property Tax");
        grid.add(payPropertyTax, 0, 4);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the mainMenu scene
         * and the main menu is assigned its dimensions
         */
        grid2.getChildren().addAll(payPropertyTax, logoutButton, menuText, registerPropertyButton, viewPropertiesButton, balanceStatementsButton, changePasswordButton);
        mainMenu = new Scene(grid2, 500, 500);


        //createAccount
        /***
         *Creates a new grid for the createAccount scene
         */
        GridPane grid3 = new GridPane();
        grid3.setVgap(10);
        grid3.setHgap(10);
        grid3.setAlignment(Pos.TOP_CENTER);
        grid3.setPadding(new Insets(10, 10, 10, 10));

        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text createAccountTextIntro = new Text("Create Account:");
        createAccountTextIntro.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));
        grid.add(createAccountTextIntro, 0, 0);

        Label createUsernameText = new Label("Create username:");
        grid.add(createUsernameText, 0, 1);

        TextField createUsernameInput = new TextField();
        createUsernameInput.setPromptText("username");
        grid.add(createUsernameInput, 1, 1);

        Label createPasswordText = new Label("Create password:");
        grid.add(createPasswordText, 0, 2);

        PasswordField createPasswordInput = new PasswordField();
        createPasswordInput.setPromptText("password");
        grid.add(createPasswordInput, 1, 2);

        Button createAccountButton2 = new Button("Create Account");
        grid.add(createAccountButton2, 1, 3);

        Label createAccountStatusText = new Label("");
        grid.add(createAccountStatusText, 1, 5);

        Button backToLoginButton = new Button("Back to login screen");
        grid.add(backToLoginButton, 1, 30);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the createAccount scene
         * and the createAccount scene is assigned its dimensions
         */
        grid3.getChildren().addAll(createAccountTextIntro, createUsernameText, createAccountButton2, createUsernameInput, createPasswordText, createPasswordInput, createAccountStatusText, backToLoginButton);
        createAccount = new Scene(grid3, 500, 500);

        //changePassword
        /***
         *Creates a new grid for the changePassword scene
         */
        GridPane grid4 = new GridPane();
        grid4.setAlignment(Pos.TOP_CENTER);
        grid4.setVgap(10);
        grid4.setHgap(10);
        grid4.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */
        Text enterNewPasswordText = new Text("Enter new password:");
        grid.add(enterNewPasswordText, 0, 0);

        TextField enterNewPasswordInput = new PasswordField();
        enterNewPasswordInput.setPromptText("password");
        grid.add(enterNewPasswordInput, 0, 1);

        Button saveNewPasswordAndLogoutButton = new Button("Save new password and logout");
        grid.add(saveNewPasswordAndLogoutButton, 0, 36);

        Button backToMainMenu6 = new Button("back");
        grid.add(backToMainMenu6, 1, 36);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the changePassword scene
         * and the changePassword scene is assigned its dimensions
         */


        grid4.getChildren().addAll(backToMainMenu6, enterNewPasswordText, enterNewPasswordInput, saveNewPasswordAndLogoutButton);
        changePassword = new Scene(grid4, 500, 500);

        //registerProperty
        /***
         *Creates a new grid for the registerProperty scene
         */
        GridPane grid5 = new GridPane();
        grid5.setAlignment(Pos.TOP_CENTER);
        grid5.setVgap(10);
        grid5.setHgap(10);
        grid5.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text enterAddressText = new Text("Property Details");
        grid.add(enterAddressText, 0, 0);
        enterAddressText.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));

        TextField firstLineText = new TextField();
        firstLineText.setPromptText("First line");
        grid.add(firstLineText, 0, 1);

        TextField secondLineText = new TextField();
        secondLineText.setPromptText("Second line");
        grid.add(secondLineText, 0, 2);

        TextField cityText = new TextField();
        cityText.setPromptText("City");
        grid.add(cityText, 0, 3);

        TextField countyText = new TextField();
        countyText.setPromptText("County");
        grid.add(countyText, 0, 4);

        TextField countryText = new TextField();
        countryText.setPromptText("Country");
        grid.add(countryText, 0, 5);

        TextField eircodeText = new TextField();
        eircodeText.setPromptText("Eircode");
        grid.add(eircodeText, 0, 6);

        ComboBox<String> comboBoxLocationType = new ComboBox<>();
        comboBoxLocationType.getItems().addAll("Village", "Small town", "Large town", "Countryside", "City");
        comboBoxLocationType.setValue("Village");
        grid.add(comboBoxLocationType, 0, 7);

        TextField estimatedValueInput = new TextField();
        estimatedValueInput.setPromptText("Estimated value");
        grid.add(estimatedValueInput, 0, 8);

        Label principalPrivateResidenceText = new Label("Is this your principal private property?");
        grid.add(principalPrivateResidenceText, 0, 9);

        ComboBox<String> comboBoxPrincipalProperty = new ComboBox<>();
        comboBoxPrincipalProperty.getItems().addAll("Yes", "No");
        comboBoxPrincipalProperty.setValue("Yes");
        grid.add(comboBoxPrincipalProperty, 0, 10);

        Label registerPropertyStatusText = new Label("");
        grid.add(registerPropertyStatusText, 0, 11);

        Button registerPropertyFinish = new Button("Finish");
        grid.add(registerPropertyFinish, 1, 16);

        Button backToMainMenu2 = new Button("back");
        grid.add(backToMainMenu2, 0, 16);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the registerProperty scene
         * and the registerProperty scene is assigned its dimensions
         */
        grid5.getChildren().addAll(backToMainMenu2, enterAddressText, firstLineText, secondLineText, cityText, countyText,
                countryText, eircodeText, comboBoxLocationType, estimatedValueInput, principalPrivateResidenceText,
                comboBoxPrincipalProperty, registerPropertyStatusText, registerPropertyFinish);
        registerProperty = new Scene(grid5, 500, 500);

        //viewproperties
        /***
         *Creates a new grid for the viewProperties scene
         */
        GridPane grid9 = new GridPane();
        grid9.setAlignment(Pos.TOP_CENTER);
        grid9.setVgap(10);
        grid9.setHgap(10);
        grid9.setPadding(new Insets(10, 10, 10, 10));

        Text viewPropertiesText = new Text("");
        grid.add(viewPropertiesText, 0, 0);

        Button backToMainMenu = new Button("back");
        grid.add(backToMainMenu, 0, 2);

        grid9.getChildren().addAll(viewPropertiesText, backToMainMenu);
        viewProperties = new Scene(grid9, 500, 500);


        //pay property tax
        /***
         *Creates a new grid for the makePayment scene
         */

        GridPane grid11 = new GridPane();
        grid11.setAlignment(Pos.TOP_CENTER);
        grid11.setVgap(10);
        grid11.setHgap(10);
        grid11.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text payPropertyTaxText = new Text("Pay property tax:");
        grid.add(payPropertyTaxText, 0, 0);
        payPropertyTaxText.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));

        Text selectPropertyText = new Text("Select property");
        grid.add(selectPropertyText, 0, 1);

        ComboBox<String> comboBoxSelectProperty = new ComboBox<>();
        grid.add(comboBoxSelectProperty, 1, 1);

        TextField propertyTaxToPay = new TextField();
        propertyTaxToPay.setPromptText("Amount to pay");
        grid.add(propertyTaxToPay, 1, 2);

        Button makePaymentButton = new Button("Make payment");
        grid.add(makePaymentButton, 1, 3);

        Text payPropertyTaxStatusText = new Text("");
        grid.add(payPropertyTaxStatusText, 1, 4);

        Button backToMainMenu7 = new Button("back");
        grid.add(backToMainMenu7, 1, 32);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the makePayment scene
         * and the makePayment scene is assigned its dimensions
         */

        grid11.getChildren().addAll(backToMainMenu7, comboBoxSelectProperty, propertyTaxToPay, makePaymentButton, selectPropertyText, payPropertyTaxText, payPropertyTaxStatusText);
        makePayment = new Scene(grid11, 500, 500);

        //balance statement form
        /***
         *Creates a new grid for the balanceStatementForm scene
         */
        GridPane grid12 = new GridPane();
        grid12.setAlignment(Pos.TOP_CENTER);
        grid12.setVgap(10);
        grid12.setHgap(10);
        grid12.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text SelectOptionsForBalance = new Text("Balance Statement Options");
        grid.add(SelectOptionsForBalance, 0, 0);
        SelectOptionsForBalance.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));

        Button backToMainMenu8 = new Button("back");
        grid.add(backToMainMenu8, 0, 33);

        Button balanceStatementsNext = new Button("Next");
        grid.add(balanceStatementsNext, 1, 33);

        Label selectPropertyBalanceText = new Label("Select property to get balance statement:");
        grid.add(selectPropertyBalanceText, 0, 1);

        ComboBox<String> selectPropertyBalance = new ComboBox<>();
        grid.add(selectPropertyBalance, 0, 2);

        Label selectYearBalanceText = new Label("Select year to get balance statement:");
        grid.add(selectYearBalanceText, 0, 3);

        TextField selectYearBalance = new TextField();
        selectYearBalance.setPromptText("year");
        grid.add(selectYearBalance, 0, 4);

        Text balanceStatementFormStatusText = new Text("");
        grid.add(balanceStatementFormStatusText, 0, 5);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the balanceStatementForm scene
         * and the balanceStatementForm scene is assigned its dimensions
         */

        grid12.getChildren().addAll(backToMainMenu8, balanceStatementsNext, SelectOptionsForBalance, selectPropertyBalance,
                selectPropertyBalanceText, selectYearBalanceText, selectYearBalance, balanceStatementFormStatusText);
        balanceStatementForm = new Scene(grid12, 500, 500);

        //view balance statement
        /***
         *Creates a new grid for the viewBalanceStatement scene
         */
        GridPane grid16 = new GridPane();
        grid16.setVgap(10);
        grid16.setHgap(10);
        grid16.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text balanceStatementText = new Text("");
        grid.add(balanceStatementText, 0, 0);

        Button backToMainMenu3 = new Button("back");
        grid.add(backToMainMenu3, 0, 1);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the viewBalanceStatement scene
         * and the viewBalanceStatement scene is assigned its dimensions
         */

        grid16.getChildren().addAll(balanceStatementText, backToMainMenu3);
        viewBalanceStatement = new Scene(grid16, 500, 500);

        //admin main menu
        /***
         *Creates a new grid for the adminMenu scene
         */
        GridPane grid10 = new GridPane();
        grid10.setAlignment(Pos.TOP_CENTER);
        grid10.setVgap(10);
        grid10.setHgap(10);
        grid10.setPadding(new Insets(10, 10, 10, 10));

        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text menuTextA = new Text("Admin Main Menu");
        menuTextA.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        grid.add(menuTextA, 0, 0);


        Button propertyTaxPaymentDataPropertyButton = new Button("Get property tax payment data for a property");
        grid.add(propertyTaxPaymentDataPropertyButton, 0, 1);

        Button propertyTaxPaymentDataOwnerButton = new Button("Get property tax payment data for a property owner");
        grid.add(propertyTaxPaymentDataOwnerButton, 0, 2);

        Button overduePropertyTaxButton = new Button("Get overdue property tax");
        grid.add(overduePropertyTaxButton, 0, 3);

        Button propertyTaxStatisticsButton = new Button("Get property tax statistics");
        grid.add(propertyTaxStatisticsButton, 0, 4);


        Button logoutButtonA = new Button("log out");
        grid.add(logoutButtonA, 0, 30);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the adminMenu scene
         * and the adminMenu scene is assigned its dimensions
         */

        grid10.getChildren().addAll(logoutButtonA, menuTextA, overduePropertyTaxButton, propertyTaxPaymentDataPropertyButton, propertyTaxPaymentDataOwnerButton, propertyTaxStatisticsButton);
        adminMenu = new Scene(grid10, 500, 500);


        //propertyTaxPaymentDataProperty scene
        /***
         *Creates a new grid for the propertyTaxPaymentDataProperty scene
         */
        GridPane grid13 = new GridPane();
        grid13.setAlignment(Pos.TOP_CENTER);
        grid13.setVgap(10);
        grid13.setHgap(10);
        grid13.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text propertyTaxDataEnterAddressText = new Text("Property Details");
        grid.add(propertyTaxDataEnterAddressText, 0, 0);
        propertyTaxDataEnterAddressText.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));

        TextField propertyTaxDataFirstLineText = new TextField();
        propertyTaxDataFirstLineText.setPromptText("First line");
        grid.add(propertyTaxDataFirstLineText, 0, 1);

        TextField propertyTaxDataSecondLineText = new TextField();
        propertyTaxDataSecondLineText.setPromptText("Second line");
        grid.add(propertyTaxDataSecondLineText, 0, 2);

        TextField propertyTaxDataCityText = new TextField();
        propertyTaxDataCityText.setPromptText("City");
        grid.add(propertyTaxDataCityText, 0, 3);

        TextField propertyTaxDataCountyText = new TextField();
        propertyTaxDataCountyText.setPromptText("County");
        grid.add(propertyTaxDataCountyText, 0, 4);

        TextField propertyTaxDataCountryText = new TextField();
        propertyTaxDataCountryText.setPromptText("Country");
        grid.add(propertyTaxDataCountryText, 0, 5);

        TextField propertyTaxDataEircodeText = new TextField();
        propertyTaxDataEircodeText.setPromptText("Eircode");
        grid.add(propertyTaxDataEircodeText, 0, 6);

        ComboBox<String> propertyTaxDataComboBoxLocationType = new ComboBox<>();
        propertyTaxDataComboBoxLocationType.getItems().addAll("Village", "Small town", "Large town", "Countryside", "City");
        propertyTaxDataComboBoxLocationType.setValue("Village");
        grid.add(propertyTaxDataComboBoxLocationType, 0, 7);

        Button backToMainMenu9 = new Button("back");
        grid.add(backToMainMenu9, 0, 15);

        Button propertyTaxDataPropertyNextButton = new Button("Next");
        grid.add(propertyTaxDataPropertyNextButton, 1, 15);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the propertyTaxPaymentDataProperty scene
         * and the propertyTaxPaymentDataProperty scene is assigned its dimensions
         */

        grid13.getChildren().addAll(propertyTaxDataEnterAddressText, propertyTaxDataFirstLineText, propertyTaxDataSecondLineText,
                propertyTaxDataCityText, propertyTaxDataCountyText, propertyTaxDataCountryText, propertyTaxDataEircodeText,
                propertyTaxDataComboBoxLocationType, propertyTaxDataPropertyNextButton, backToMainMenu9);
        propertyTaxPaymentDataProperty = new Scene(grid13, 500, 500);

        //getPropertyTaxPaymentDataOwner scene
        /***
         *Creates a new grid for the propertyTaxPaymentDataOwner scene
         */

        GridPane grid18 = new GridPane();
        grid18.setAlignment(Pos.TOP_CENTER);
        grid18.setVgap(10);
        grid18.setHgap(10);
        grid18.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text PropertyTaxDataOwnerText = new Text("Enter property owner name");
        grid.add(PropertyTaxDataOwnerText, 0, 0);
        PropertyTaxDataOwnerText.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20));

        TextField propertyTaxDataOwnerTextField = new TextField();
        propertyTaxDataOwnerTextField.setPromptText("Property owner name");
        grid.add(propertyTaxDataOwnerTextField, 0, 1);

        Button backToMainMenu12 = new Button("back");
        grid.add(backToMainMenu12, 0, 15);

        Button propertyTaxDataOwnerNextButton = new Button("Next");
        grid.add(propertyTaxDataOwnerNextButton, 1, 15);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the propertyTaxPaymentDataOwner scene
         * and the propertyTaxPaymentDataOwner scene is assigned its dimensions
         */

        grid18.getChildren().addAll(PropertyTaxDataOwnerText, propertyTaxDataOwnerTextField, propertyTaxDataOwnerNextButton, backToMainMenu12);
        propertyTaxPaymentDataOwner = new Scene(grid18, 500, 500);

        //genericAdminViewData
        /***
         *Creates a new grid for the genericAdminViewData scene
         */

        GridPane grid17 = new GridPane();
        grid17.setAlignment(Pos.TOP_CENTER);
        grid17.setVgap(10);
        grid17.setHgap(10);
        grid17.setPadding(new Insets(10, 10, 10, 10));
        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Text adminViewDataText = new Text("");
        grid.add(adminViewDataText, 0, 0);

        Button adminViewDataBack = new Button("back");
        grid.add(adminViewDataBack, 0, 2);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the genericAdminViewData scene
         * and the genericAdminViewData scene is assigned its dimensions
         */

        grid17.getChildren().addAll(adminViewDataText, adminViewDataBack);
        genericAdminViewData = new Scene(grid17, 500, 500);

        //overduePropertyTax
        /***
         *Creates a new grid for the overduePropertyTax scene
         */

        GridPane grid14 = new GridPane();
        grid14.setAlignment(Pos.TOP_CENTER);
        grid14.setVgap(10);
        grid14.setHgap(10);
        grid14.setPadding(new Insets(10, 10, 10, 10));

        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Label overduePropertyTaxYearText = new Label("Enter year to view data for:");
        grid.add(overduePropertyTaxYearText, 0, 0);

        TextField overduePropertyTaxYearField = new TextField();
        overduePropertyTaxYearField.setPromptText("Year");
        grid.add(overduePropertyTaxYearField, 0, 1);

        Label overduePropertyTaxEircodeText = new Label("Routing key of area (leave blank for whole country)");
        grid.add(overduePropertyTaxEircodeText, 0, 2);

        TextField overduePropertyTaxEircodeField = new TextField();
        overduePropertyTaxEircodeField.setPromptText("Routing key");
        grid.add(overduePropertyTaxEircodeField, 0, 3);

        Button backToMainMenu10 = new Button("back");
        grid.add(backToMainMenu10, 0, 20);

        Button overduePropertyTaxNext = new Button("Next");
        grid.add(overduePropertyTaxNext, 1, 20);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the overduePropertyTax scene
         * and the overduePropertyTax scene is assigned its dimensions
         */

        grid14.getChildren().addAll(overduePropertyTaxYearText, overduePropertyTaxYearField, overduePropertyTaxEircodeText,
                overduePropertyTaxEircodeField, overduePropertyTaxNext, backToMainMenu10);
        overduePropertyTax = new Scene(grid14, 500, 500);


        //propertyTaxStatistics
        /***
         *Creates a new grid for the propertyTaxStatistics scene
         */
        GridPane grid15 = new GridPane();
        grid15.setAlignment(Pos.TOP_CENTER);
        grid15.setVgap(10);
        grid15.setHgap(10);
        grid15.setPadding(new Insets(10, 10, 10, 10));

        /***
         * Buttons/text/textfields are made and assigned positions on the grid
         */

        Label propertyTaxStatisticsYearText = new Label("Enter year to view statistics for:");
        grid.add(propertyTaxStatisticsYearText, 0, 0);

        TextField propertyTaxStatisticsYearField = new TextField();
        propertyTaxStatisticsYearField.setPromptText("Year");
        grid.add(propertyTaxStatisticsYearField, 0, 1);

        Label propertyTaxStatisticsEircodeText = new Label("Routing key of area");
        grid.add(propertyTaxStatisticsEircodeText, 0, 2);

        TextField propertyTaxStatisticsEircodeField = new TextField();
        propertyTaxStatisticsEircodeField.setPromptText("Routing key");
        grid.add(propertyTaxStatisticsEircodeField, 0, 3);

        Button backToMainMenu11 = new Button("back");
        grid.add(backToMainMenu11, 0, 20);

        Button propertyTaxStatisticsNext = new Button("Next");
        grid.add(propertyTaxStatisticsNext, 1, 20);

        /***
         *adds all the buttons and text to the grid.
         * the grid is then added to the propertyTaxStatistics scene
         * and the propertyTaxStatistics scene is assigned its dimensions
         */

        grid15.getChildren().addAll(propertyTaxStatisticsYearText, propertyTaxStatisticsYearField, propertyTaxStatisticsEircodeText,
                propertyTaxStatisticsEircodeField, propertyTaxStatisticsNext, backToMainMenu11);
        propertyTaxStatistics = new Scene(grid15, 500, 500);

        //button events
        /***
         *Gives buttons functionality to swap between scenes
         */

        balanceStatementsButton.setOnAction(e -> balanceStatementsClicked(selectPropertyBalance));
        payPropertyTax.setOnAction(e -> payPropertyTaxClicked(comboBoxSelectProperty));

        propertyTaxStatisticsButton.setOnAction(e -> window.setScene(propertyTaxStatistics));
        overduePropertyTaxButton.setOnAction(e -> window.setScene(overduePropertyTax));
        propertyTaxPaymentDataPropertyButton.setOnAction(e -> window.setScene(propertyTaxPaymentDataProperty));
        propertyTaxPaymentDataOwnerButton.setOnAction(e -> window.setScene(propertyTaxPaymentDataOwner));

        backToMainMenu11.setOnAction(e -> window.setScene(adminMenu));
        backToMainMenu10.setOnAction(e -> window.setScene(adminMenu));
        backToMainMenu9.setOnAction(e -> window.setScene(adminMenu));
        backToMainMenu12.setOnAction(e -> window.setScene(adminMenu));
        backToMainMenu8.setOnAction(e -> window.setScene(mainMenu));
        backToMainMenu7.setOnAction(e -> window.setScene(mainMenu));
        backToMainMenu.setOnAction(e -> window.setScene(mainMenu));
        backToMainMenu6.setOnAction(e -> window.setScene(mainMenu));
        backToMainMenu2.setOnAction(e -> window.setScene(mainMenu));
        backToMainMenu3.setOnAction(e -> window.setScene(balanceStatementForm));
        adminViewDataBack.setOnAction(e -> window.setScene(adminMenu));

        propertyTaxDataPropertyNextButton.setOnAction(e -> propertyTaxDataPropertyNextClicked(propertyTaxDataFirstLineText.getText(),
                propertyTaxDataSecondLineText.getText(), propertyTaxDataCityText.getText(), propertyTaxDataCountyText.getText(),
                propertyTaxDataCountryText.getText(), propertyTaxDataEircodeText.getText(), propertyTaxDataComboBoxLocationType.getValue(), adminViewDataText));
        propertyTaxDataOwnerNextButton.setOnAction(e -> propertyTaxDataOwnerNextClicked(propertyTaxDataOwnerTextField.getText(), adminViewDataText));
        overduePropertyTaxNext.setOnAction(e -> overduePropertyTaxNextClicked(overduePropertyTaxYearField.getText(), overduePropertyTaxEircodeField.getText(), adminViewDataText));
        propertyTaxStatisticsNext.setOnAction(e -> propertyTaxStatisticsNextClicked(propertyTaxStatisticsYearField.getText(), propertyTaxStatisticsEircodeField.getText(), adminViewDataText));

        viewPropertiesButton.setOnAction(e -> viewPropertiesClicked(viewPropertiesText));
        balanceStatementsNext.setOnAction(e -> viewBalanceStatementClicked(balanceStatementFormStatusText, selectPropertyBalance.getValue(), selectYearBalance.getText(), balanceStatementText));
        makePaymentButton.setOnAction(e -> payPropertyTaxOnProperty(payPropertyTaxStatusText, propertyTaxToPay.getText(), comboBoxSelectProperty.getValue()));

        registerPropertyButton.setOnAction(e -> window.setScene(registerProperty));
        saveNewPasswordAndLogoutButton.setOnAction(e -> changePasswordClicked(enterNewPasswordInput.getText()));
        changePasswordButton.setOnAction(e -> window.setScene(changePassword));
        createAccountButton.setOnAction(e -> window.setScene(createAccount));
        loginButton.setOnAction(e -> logInClicked(usernameInput.getText(), passwordInput.getText(), invalidLoginText));
        createAccountButton2.setOnAction(e -> createAccountClicked(createUsernameInput.getText(), createPasswordInput.getText(), createAccountStatusText));
        logoutButton.setOnAction(e -> window.setScene(login));
        logoutButtonA.setOnAction(e -> window.setScene(login));
        backToLoginButton.setOnAction(e -> window.setScene(login));
        registerPropertyFinish.setOnAction(e -> registerPropertyFinishClicked(firstLineText.getText(), secondLineText.getText(),
                cityText.getText(), countyText.getText(), countryText.getText(), eircodeText.getText(),
                comboBoxLocationType.getValue(), estimatedValueInput.getText(), comboBoxPrincipalProperty.getValue().equals("Yes"), registerPropertyStatusText));

        /***
         * sets the fist scene as the login scene
         * sets the title of the window
         * shows the window
         */
        window.setScene(login);
        window.setTitle("TaxIrelandSolutions Ltd");
        window.show();
    }

    /***
     *This method is used to check if a password is entered correctly or not
     * if it is it changes the scene, if not it tells user it is invalid
     * and changes the scene.
     */
    private void logInClicked(String username, String password, Label invalidLoginText) {
        User user = tms.attemptLogin(username, password);
        if (user == null) {
            invalidLoginText.setVisible(true);
        } else if (user instanceof PropertyOwner) {
            invalidLoginText.setVisible(false);
            window.setScene(mainMenu);
        } else if (user instanceof Administrator) {
            invalidLoginText.setVisible(false);
            window.setScene(adminMenu);
        }
    }

    /***
     *This method checks to see if a new account name is unique
     * if it is it adds the new account to the list,
     * if not it tells user their username already exists
     */
    private void createAccountClicked(String username, String password, Label createAccountStatusText) {
        if (!tms.createAccount(username, password)) {
            createAccountStatusText.setText("A user with that name already exists");
        } else {
            createAccountStatusText.setText("Created successfully!");
        }
    }

    /***
     *This method changes the users password and changes the scene
     */
    private void changePasswordClicked(String newPassword) {
        tms.changeCurrentUserPassword(newPassword);
        window.setScene(login);
    }

    /***
     *This method is used when a user trys to finish entering a property.
     * It checks to see it the eircode is valid and registers the property if it is.
     * and changes the scene.
     */
    private void registerPropertyFinishClicked(String firstLine, String secondLine, String city, String county, String country,
                                               String eircodeString, String locationType, String estimatedValue, boolean principalProperty, Label registerPropertyStatusText) {
        Eircode eircode = new Eircode();
        if (!eircode.fromString(eircodeString)) {
            registerPropertyStatusText.setText("Invalid Eircode");
            return;
        }
        double valueDouble;
        try {
            valueDouble = Double.parseDouble(estimatedValue);
        } catch (NumberFormatException e) {
            registerPropertyStatusText.setText("Estimated Value is not a valid number");
            return;
        }
        Address a = new Address(firstLine, secondLine, city, county, country, locationType, eircode);
        Property property = new Property(a, tms.getLoggedInUser().getName(), valueDouble, principalProperty, Year.now().getValue());
        tms.registerProperty(property);
        registerPropertyStatusText.setText("");
        window.setScene(mainMenu);
    }

    /***
     *This method is use when a user clicks the viewproperties button.
     * It displays the properties owned by the user
     * and changes the scene.
     */
    private void viewPropertiesClicked(Text viewPropertiesText) {
        viewPropertiesText.setText(tms.getOwnedPropertiesListString());
        window.setScene(viewProperties);
    }

    /***
     *This method gives a balances the statements when clicked
     * and changes the scene.
     */
    private void balanceStatementsClicked(ComboBox<String> selectProperty) {
        List<Property> properties = tms.getOwnedProperties();
        selectProperty.getItems().clear();
        selectProperty.getItems().add("0 All properties");
        int i = 1;
        for (Property p : properties) {
            selectProperty.getItems().add(Integer.toString(i) + " " + p.getAddress().toString());
            i++;
        }
        selectProperty.setValue("0 All properties");
        window.setScene(balanceStatementForm);
    }

    /***
     *This method displays the balance statements when clicked.
     * And checks to see if the year is valid.
     * and changes the scene.
     */
    private void viewBalanceStatementClicked(Text balanceStatementFormStatusText, String selectProperty, String selectYearBalance, Text balanceStatementText) {
        int year = 0;
        try {
            year = Integer.parseInt(selectYearBalance);
        } catch (NumberFormatException e) {
            balanceStatementFormStatusText.setText("Year is not a valid number");
            return;
        }
        int selectedPropertyIndex = Integer.parseInt(selectProperty.split(" ")[0]);

        if (selectedPropertyIndex == 0) {
            balanceStatementText.setText(tms.getBalanceStatement(tms.getOwnedProperties(), year));
        } else {
            Property p = tms.getOwnedProperties().get(selectedPropertyIndex - 1);
            balanceStatementText.setText(tms.getBalanceStatement(Arrays.asList(p), year));
        }

        balanceStatementFormStatusText.setText("");
        window.setScene(viewBalanceStatement);
    }

    /***
     *This Method lets the user select a property to pay their property tax
     * and changes the scene.
     */

    private void payPropertyTaxClicked(ComboBox<String> selectProperty) {
        List<Property> properties = tms.getOwnedProperties();
        selectProperty.getItems().clear();
        int i = 0;
        for (Property p : properties) {
            selectProperty.getItems().add(Integer.toString(i) + " " + p.getAddress().toString());
            i++;
        }
        selectProperty.setValue(selectProperty.getItems().get(0));
        window.setScene(makePayment);
    }

    /***
     *This Method lets the user  pay their property tax when clicked
     * It also checks to see if the amount to pay is valid
     * and changes the scene.
     */
    private void payPropertyTaxOnProperty(Text payPropertyTaxStatusText, String amountString, String selectProperty) {
        double amountDouble;
        try {
            amountDouble = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            payPropertyTaxStatusText.setText("Amount to pay is not a valid number");
            return;
        }
        int selectedPropertyIndex = Integer.parseInt(selectProperty.split(" ")[0]);
        Property p = tms.getOwnedProperties().get(selectedPropertyIndex);
        tms.payPropertyTax(p, amountDouble);
        payPropertyTaxStatusText.setText("");
        window.setScene(mainMenu);
    }

    /***
     *This Method lets the admin select a property that they wish to view its property tax data
     * and changes the scene.
     */
    private void propertyTaxDataPropertyNextClicked(String firstLine, String secondLine, String city, String county,
                                                    String country, String eircodeString, String locationType, Text adminViewDataText) {
        Eircode eircode = new Eircode();
        if (!eircode.fromString(eircodeString)) {
            adminViewDataText.setText("Invalid Eircode");
        } else {
            Address a = new Address(firstLine, secondLine, city, county, country, locationType, eircode);
            adminViewDataText.setText(tms.getTaxDataForPropertyAddress(a));
        }
        window.setScene(genericAdminViewData);
    }

    /***
     *This Method lets the admin view property tax data
     * and changes the scene.
     */
    private void propertyTaxDataOwnerNextClicked(String propertyOwnerName, Text adminViewDataText) {
        System.out.println(propertyOwnerName);
        adminViewDataText.setText(tms.getTaxDataForPropertyOwner(propertyOwnerName));
        window.setScene(genericAdminViewData);
    }

    /***
     *This Method lets the admin view the overdue property tax
     * It also checks to see it the routing key and that the year is valid
     * and changes the scene.
     */
    private void overduePropertyTaxNextClicked(String yearString, String routingKey, Text adminViewDataText) {
        if (routingKey.length() != 0 && routingKey.length() != 3) {
            adminViewDataText.setText("Invalid routing key");
        } else {
            int year = 0;
            try {
                year = Integer.parseInt(yearString);
                adminViewDataText.setText(tms.getOverduePropertyTaxByArea(year, routingKey));
            } catch (NumberFormatException e) {
                adminViewDataText.setText("Year is not a valid number");
            }
        }
        window.setScene(genericAdminViewData);
    }

    /***
     *This Method lets the admin view the property tax statistics
     * It also checks to see it the routing key is valid and that the year is valid
     * and changes the scene.
     */

    private void propertyTaxStatisticsNextClicked(String yearString, String routingKey, Text adminViewDataText) {
        if (routingKey.length() != 3) {
            adminViewDataText.setText("Invalid routing key");
        } else {
            int year = 0;
            try {
                year = Integer.parseInt(yearString);
                adminViewDataText.setText(tms.getPropertyTaxStatistics(year, routingKey));
            } catch (NumberFormatException e) {
                adminViewDataText.setText("Year is not a valid number");
            }
        }
        window.setScene(genericAdminViewData);
    }
}