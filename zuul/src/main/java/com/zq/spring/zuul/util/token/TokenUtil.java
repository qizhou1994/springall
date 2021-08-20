package com.zq.spring.zuul.util.token;

public class TokenUtil {

    public static final String AES_KEY_TOKEN = "ety_token_zzzzzq";
    public static final String RAS_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCA28eLNejumAlqvXlf4Q32bS76VBWhLqk07X/NgGdKV2NkIXBeIJozhiZkOJWGO57gNRdu5DqQD30jc4glhOAIjwPXFej7T/yMadGLFpiCrG9Og0tKMuHSuG7zEpqh2Qihztro9xdXP/yWk2JUj/fWUoCgIWuCwGWNx487T/sSBQIDAQAB";
    public static final String RAS_PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIDbx4s16O6YCWq9eV/hDfZtLvpUFaEuqTTtf82AZ0pXY2QhcF4gmjOGJmQ4lYY7nuA1F27kOpAPfSNziCWE4AiPA9cV6PtP/Ixp0YsWmIKsb06DS0oy4dK4bvMSmqHZCKHO2uj3F1c//JaTYlSP99ZSgKAha4LAZY3HjztP+xIFAgMBAAECgYBx6FZblBtFlrZ0WevKOrrKBLhLZzr7kbDX7b5VHdWw3NEqeXjIbE+DjmmvnGlpEJkgHy6Iw02VfWukhANtDymufSxw8AIubF5MSP8FthQ787QLyU1Q600X4BJRHURB9JfOBgiM97/qsr2ynbx8Yo/L+qKnYNcv5ENIPInm7XE4XQJBAOWcyB1kEyQMZyamSex7kwqazSwmFyWhVbfS5hpAroomGZ38lv7LyRCWSD8pKNNW9lHvldbf+7Fr1mld/mCI/68CQQCPqs77r961Vgm8WxqJ2UPSPllTZKmjlS885nEl+9KPc1GhqIQEWtV/s9fBOm4AneTvq5olbsC9pRZ3w430TSKLAkEAxbEJMxT4ze7H2SUPPMbgwR6rTDm3cDTKQq0YZL4QCO3o3Hef4dy/TsK1jXv4pI2ZIs6vKgRLBmUchDfjTmZmDQJAcgVBIrWQzmBLd8biSBc74WeEY1AX5nEnPXEyyc+TbDA80E07AW3J1gE4se2akji+Eo0h2KWOqLSWIVT9m6+AQwJBAJRZJL2rcE4Lvyhe69fITlwQzODxJKMXtTcAC3R+nDs56DKumVTzqa422j/QMilC3OaInlMDBS/mhnE4xXU94jU=";
    /**
     * 生成token
     *
     * @param user_pwd_input
     * @return
     */
    public static String createToken(String user_pwd_input) {
        String s = null;
        try {
            s = RSAUtils.encrypt(user_pwd_input,RAS_PUB_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    public static String getUserKeyByToken(String token) throws Exception {
        String s = null;
            s = RSAUtils.decrypt(token,RAS_PRI_KEY);
        return s;
    }




}
