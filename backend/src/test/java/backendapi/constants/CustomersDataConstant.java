package backendapi.constants;

import backendapi.model.Gender;

import java.time.LocalDateTime;

import static backendapi.model.Gender.FEMALE;
import static backendapi.model.Gender.MALE;
import static java.time.LocalDateTime.of;

public class CustomersDataConstant {

    public static final Integer WHAZT_ID = 1;
    public static final Integer JOAO_ID = 2;
    public static final Integer MARIA_ID = 3;
    public static final Integer PEDRO_ID = 4;
    public static final Integer ANA_ID = 5;
    public static final String WHAZT = "Whatz";
    public static final String JOAO = "João";
    public static final String MARIA = "Maria";
    public static final String PEDRO = "Pedro";
    public static final String ANA = "Ana";
    public static final String WHAZT_EMAIL = "whatz.junior@gmail.com";
    public static final String JOAO_EMAIL = "joao@gmail.com";
    public static final String MARIA_EMAIL = "maria@gmail.com";
    public static final String PEDRO_EMAIL = "pedro@gmail.com";
    public static final String ANA_EMAIL = "ana@gmail.com";
    public static final String WHAZT_PASSWORD = "123456789";
    public static final String JOAO_PASSWORD = "987654321";
    public static final String MARIA_PASSWORD = "qwertyui";
    public static final String PEDRO_PASSWORD = "asdfghjk";
    public static final String ANA_PASSWORD = "zxcvbnm";
    public static final Gender WHAZT_GENDER = FEMALE;
    public static final Gender JOAO_GENDER = MALE;
    public static final Gender MARIA_GENDER = FEMALE;
    public static final Gender PEDRO_GENDER = MALE;
    public static final Gender ANA_GENDER = FEMALE;
    public static final LocalDateTime WHAZT_BIRTHDATE = of(1990, 11, 9, 22, 13);
    public static final LocalDateTime JOAO_BIRTHDATE = of(1985, 5, 15, 10, 30);
    public static final LocalDateTime MARIA_BIRTHDATE = of(1992, 3, 25, 8, 45);
    public static final LocalDateTime PEDRO_BIRTHDATE = of(1988, 8, 12, 18, 20);
    public static final LocalDateTime ANA_BIRTHDATE = of(1995, 10, 4, 14, 55);
    public static final String WHAZT_PROFILE_IMAGE_ID = "image1";
    public static final String JOAO_PROFILE_IMAGE_ID = "image2";
    public static final String MARIA_PROFILE_IMAGE_ID = "image3";
    public static final String PEDRO_PROFILE_IMAGE_ID = "image4";
    public static final String ANA_PROFILE_IMAGE_ID = "image5";
}
