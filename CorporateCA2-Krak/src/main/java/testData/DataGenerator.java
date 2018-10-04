package testData;

import java.util.Random;

/**
 *
 * @author Rasmus
 */
public class DataGenerator {

    private final Random random = new Random();

    //Address
    private final String[] addressStreet = {"Lærkevej ", "Birkevej ", "Vibevej ", "Vinkelvej ", "Østergade ", "Engvej ", "Vestergade ", "Møllevej ", "Kirkevej "};
    private final String[] addressInfo = {"Roligt kvarter", "Larmende naboer", "Kendis som nabo", "Huset mangler en kærlig hånd", "Meget moderne", "Tidligere sindssygeanstalt", "Party kvarter", "Donald Trump was here", "Perfekt til naturelskere"};

    //City
    private final String[] city = {"Scanning", "Højetaastrup", "København", "København Ø", "Lyngby", "Næstved", "Herning", "Hellerup", "Vanløse"};

    //Company
    private final String[] companyName = {"Xorporate", "BusinessFix", "CphBusiness", "Triprise", "AdamInc", "InnoNet", "Facebook", "Twitter", "Instagram"};
    private final String[] companydesc = {"Lots of income", "Customers are not happy", "All money on marketing", "Soon bankrupt", "Food is key", "Very corporate", "No money", "Loved by many", "Hated by everyone"};

    //Hobby
    private final String[] hobbyName = {"Chess", "Gaming", "Golf", "Fitness", "Twerking", "Ballet", "Nudist", "Eating", "Programming"};
    private final String[] hobbydesc = {"Very relaxing", "Exciting and modern", "Possibly expensive", "Timeconsuming", "Exercise for the brain", "Will increase your IQ", "Fun with friends", "Group activities", "Will make your day!"};

    //Person
    private final String[] fnames = {"Jesper", "Lars", "Jeppe", "Nikolai", "Adam", "Karen", "Elisa", "Benjamin", "Lotte"};
    private final String[] lnames = {"Hansen", "Larsen", "Andersen", "Perlt", "Strom", "Rasmussen", "Rusbjerg", "Helsgaun", "Lass"};
    
    //Phone
    private final String[] phoneDesc = {"Good camera", "Long lasting battery", "10/10 quality", "4K videos", "Very fast", "Wireless charging", "Sends secret info to Google", "Beautiful looking", "No buttons.. All touch"};

    public String generateData() {
        String res = "";
        res += generateAddress();
        res += generateAddressCityInfo();
        res += generateCityInfo();
        res += generateCompany();
        res += generateHobby();
        res += generateHobbyPerson();
        res += generateInfoEntity();
        res += generatePerson();
        res += generatePhone();
        return res;
    }
    
    private String generatePhone(){
        String res = "";
        int phone = 12345678;
        for (int i = 1; i <= 500; i++) {
            int descIndex = random.nextInt(9);
            res += "INSERT INTO `PHONE` VALUES (" + i + ",'" + phoneDesc[descIndex] + "'," + phone + "," + i + ");\n";
            phone += 123456;
        }
        return res;
    }

    private String generatePerson() {
        String res = "";
        for (int i = 1; i <= 500; i++) {
            int fNameIndex = random.nextInt(9);
            int lNameIndex = random.nextInt(9);
            res += "INSERT INTO `PERSON` VALUES (" + i + ",'" + fnames[fNameIndex] + "','" + lnames[lNameIndex] + "');\n";
        }
        return res;
    }
    
    private String generateInfoEntity() {
        String res = "";
        for (int i = 1; i <= 500; i++) {
            int nameIndex = random.nextInt(9);
            res += "INSERT INTO `INFOENTITY` VALUES (" + i + ",'P','" + fnames[nameIndex] + i + "@gmail.com" + "');\n";
            res += "INSERT INTO `INFOENTITY` VALUES (" + i + ",'C','" + companyName[nameIndex] + i + "@" + companyName[nameIndex] + ".dk');\n";
        }
        return res;
    }

    private String generateHobbyPerson() {
        String res = "";
        for (int i = 1; i <= 500; i++) {
            res += "INSERT INTO `HOBBY_PERSON` VALUES (" + i + "," + i + ");\n";
        }
        return res;
    }

    private String generateHobby() {
        String res = "";
        for (int i = 1; i <= 500; i++) {
            int hobbyNameIndex = random.nextInt(9);
            int hobbyDescIndex = random.nextInt(9);
            res += "INSERT INTO `HOBBY` VALUES (" + i + ",'" + hobbydesc[hobbyDescIndex] + "','" + hobbyName[hobbyNameIndex] + "');\n";
        }
        return res;
    }

    private String generateCompany() {
        String res = "";
        int cvr = 10000000;
        int marketValue = 100000000;
        int empCount = 10;
        for (int i = 1; i <= 500; i++) {
            int companyNameIndex = random.nextInt(9);
            int companyDescIndex = random.nextInt(9);
            res += "INSERT INTO `COMPANY` VALUES (" + i + "," + cvr + ",'" + companydesc[companyDescIndex] + "'," + marketValue + ",'" + companyName[companyNameIndex] + "'," + empCount + ");\n";
            cvr += 123456;
            marketValue += 125000;
            empCount += 15;
        }
        return res;
    }

    private String generateCityInfo() {
        String res = "";
        int zip = 1000;
        for (int i = 1; i <= 500; i++) {
            int cityIndex = random.nextInt(9);
            res += "INSERT INTO `CITYINFO` VALUES (" + i + ",'" + city[cityIndex] + "'," + zip++ + ");\n";
        }
        return res;
    }

    private String generateAddressCityInfo() {
        String res = "";
        for (int i = 1; i <= 500; i++) {
            res += "INSERT INTO `ADDRESS_INFOENTITY` VALUES (" + i + "," + i + ");\n";
        }
        return res;
    }

    private String generateAddress() {
        String res = "";
        for (int i = 1; i <= 500; i++) {
            int streetIndex = random.nextInt(9);
            int infoIndex = random.nextInt(9);
            res += "INSERT INTO `ADDRESS` VALUES (" + i + ",'" + addressInfo[infoIndex] + "','" + addressStreet[streetIndex] + i + "'," + i + ");\n";
        }
        return res;
    }

    public static void main(String[] args) {
        DataGenerator dg = new DataGenerator();
        System.out.println(dg.generateData());
    }
}
