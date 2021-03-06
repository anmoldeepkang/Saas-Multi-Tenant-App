//export const API_BASE_URL = 'http://SaasBackend-env.eba-p2gzsrpp.us-east-1.elasticbeanstalk.com';
export const API_BASE_URL = 'http://localhost:5000';
export const ACCESS_TOKEN = 'accessToken';

//export const OAUTH2_REDIRECT_URI = 'http://saas-frontend.s3-website-us-east-1.amazonaws.com/oauth2/redirect'
export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect'

export const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = API_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;
