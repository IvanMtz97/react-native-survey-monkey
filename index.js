
import { NativeModules } from 'react-native';

const { RNSurveyMonkey } = NativeModules;

export default {
    init: (appName, hash) =>  RNSurveyMonkey.init(appName, hash),
    takeSurvey: (appName, hash) =>  RNSurveyMonkey.takeSurvey(appName, hash)
};
