package KFC_SHOPPING_SYSTEM;

import java.util.Observable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ajit Singh ID: 19070642
 */
public class SystemModel extends Observable {

    protected final String FAQFile = "./resources/Shop'sFAQ.txt"; // file location   
    private static final String URL = "jdbc:derby:KFC_DB_Ebd;create=true";
    private ResultSet rs = null;
    private DBManager DBM;
    private PreparedStatement ps;
    private final String aCustomerTable = "CUSTOMER";
    private final String aManagerTable = "MANAGER";    

    public SystemModel() {        
        System.out.println("[KFC MODEL]");           
        this.DBM = new DBManager(URL);
        this.DBM.createCustomerTable(aCustomerTable);
        this.DBM.createManagerTable(aManagerTable);                
    }
//CUSTOMER LOGIN----------------------------------------------------------------

    /**
     * @param aView
     * @param aHomeView
     * @param aMgrView
     */
    public void performCustomerLogin(LoginView aView, HomeView aHomeView, ManagerView aMgrView) {
        this.DBM = new DBManager(URL);     
        CustomerLogin aCL = new CustomerLogin();
        aCL.verifyCustomerLogin(aView, aHomeView, aCustomerTable, DBM, ps, rs);
    }

//CUSTOMER REGISTRATION---------------------------------------------------------
    /**
     * @param aRegView
     * @param aView
     * @param aHomeView
     */
    public void createNewCustomerAccount(RegistrationView aRegView, LoginView aView, HomeView aHomeView) {
        CustomerRegistration aCR = new CustomerRegistration();
        aCR.aNewCustomer(aRegView, aCustomerTable, DBM, URL, aView, aHomeView);
    }

//MANAGER-----------------------------------------------------------------------  
    /**
     * @param aView
     * @param aMgrView
     */
    public void performManagerLogin(LoginView aView, ManagerView aMgrView) {
        this.DBM = new DBManager(URL);  
        ManagerLogin aML = new ManagerLogin();
        aML.verifyManagerLogin(aView, aMgrView, aManagerTable, DBM, ps, rs);

    }

//FAQ--------------------------------------------------------
    /**     
     * @param aView
     * @param FAQView 
     */
    public void viewFAQ(LoginView aView, FAQPageView FAQView) {
        this.DBM = new DBManager(URL);    
        aView.aLoginPageFrame.setVisible(false);
        FAQView.aFAQFrame.setVisible(true);
        File_IO aFIO = new File_IO();
        aFIO.readFAQFile(FAQView, FAQFile);
    }

//------------------------------------------------------------------------------       
}
