package backendapi.integration.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerJsonUtils {

    public static String customerJson() {
        return """
                [
                  {"id":1,"name":"Whatz","email":"whatz.junior@gmail.com","birthdate":"1990-11-09 22:13:00","gender":"FEMALE","password":"123456789","profileImageId":"image1"},
                  {"id":2,"name":"João","email":"joao@gmail.com","birthdate":"1985-05-15 10:30:00","gender":"MALE","password":"987654321","profileImageId":"image2"},
                  {"id":3,"name":"Maria","email":"maria@gmail.com","birthdate":"1992-03-25 08:45:00","gender":"FEMALE","password":"qwertyui","profileImageId":"image3"},
                  {"id":4,"name":"Pedro","email":"pedro@gmail.com","birthdate":"1988-08-12 18:20:00","gender":"MALE","password":"asdfghjk","profileImageId":"image4"},
                  {"id":5,"name":"Ana","email":"ana@gmail.com","birthdate":"1995-10-04 14:55:00","gender":"FEMALE","password":"zxcvbnm","profileImageId":"image5"},
                  {"id":6,"name":"Gabriel","email":"gabriel@gmail.com","birthdate":"1998-12-30 06:40:00","gender":"MALE","password":"qazwsxedc","profileImageId":"image6"},
                  {"id":7,"name":"Fernanda","email":"fernanda@gmail.com","birthdate":"1993-07-20 16:10:00","gender":"FEMALE","password":"rfvtgbyh","profileImageId":"image7"},
                  {"id":8,"name":"Lucas","email":"lucas@gmail.com","birthdate":"1987-09-18 11:25:00","gender":"MALE","password":"ujmikolp","profileImageId":"image8"},
                  {"id":9,"name":"Carla","email":"carla@gmail.com","birthdate":"1991-02-28 09:05:00","gender":"FEMALE","password":"plmoknijb","profileImageId":"image9"},
                  {"id":10,"name":"Rafael","email":"rafael@gmail.com","birthdate":"1989-04-08 20:45:00","gender":"MALE","password":"ytrewq","profileImageId":"image10"},
                  {"id":11,"name":"Alice","email":"alice@gmail.com","birthdate":"1994-06-17 17:30:00","gender":"FEMALE","password":"password123","profileImageId":"image11"},
                  {"id":12,"name":"Bruno","email":"bruno@gmail.com","birthdate":"1997-09-23 13:15:00","gender":"MALE","password":"securepass","profileImageId":"image12"},
                  {"id":13,"name":"Juliana","email":"juliana@gmail.com","birthdate":"1990-08-03 09:40:00","gender":"FEMALE","password":"ilovecoding","profileImageId":"image13"},
                  {"id":14,"name":"Marcos","email":"marcos@gmail.com","birthdate":"1986-12-12 22:00:00","gender":"MALE","password":"letmein","profileImageId":"image14"},
                  {"id":15,"name":"Laura","email":"laura@gmail.com","birthdate":"1993-04-27 15:20:00","gender":"FEMALE","password":"password1234","profileImageId":"image15"},
                  {"id":16,"name":"Felipe","email":"felipe@gmail.com","birthdate":"1998-01-10 08:55:00","gender":"MALE","password":"gamer123","profileImageId":"image16"},
                  {"id":17,"name":"Amanda","email":"amanda@gmail.com","birthdate":"1995-11-08 12:10:00","gender":"FEMALE","password":"soccer123","profileImageId":"image17"},
                  {"id":18,"name":"Matheus","email":"matheus@gmail.com","birthdate":"1988-10-01 07:45:00","gender":"MALE","password":"hello123","profileImageId":"image18"},
                  {"id":19,"name":"Caroline","email":"caroline@gmail.com","birthdate":"1992-07-14 18:30:00","gender":"FEMALE","password":"world123","profileImageId":"image19"},
                  {"id":20,"name":"Diego","email":"diego@gmail.com","birthdate":"1987-03-19 10:15:00","gender":"MALE","password":"letsgo123","profileImageId":"image20"},
                  {"id":21,"name":"Isabela","email":"isabela@gmail.com","birthdate":"1991-05-05 14:50:00","gender":"FEMALE","password":"sunshine123","profileImageId":"image21"},
                  {"id":22,"name":"Ricardo","email":"ricardo@gmail.com","birthdate":"1989-02-28 19:25:00","gender":"MALE","password":"qwerty123","profileImageId":"image22"},
                  {"id":23,"name":"Beatriz","email":"beatriz@gmail.com","birthdate":"1996-08-11 11:00:00","gender":"FEMALE","password":"password12345","profileImageId":"image23"},
                  {"id":24,"name":"Henrique","email":"henrique@gmail.com","birthdate":"1984-09-30 16:35:00","gender":"MALE","password":"secure123","profileImageId":"image24"},
                  {"id":25,"name":"Mariana","email":"mariana@gmail.com","birthdate":"1997-11-22 20:05:00","gender":"FEMALE","password":"iloveyou123","profileImageId":"image25"}
                ]""";
    }

    public static String customerJsonPage() {
        return "";
    }

}
