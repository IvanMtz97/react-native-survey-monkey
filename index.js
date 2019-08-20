
import { NativeModules } from 'react-native';

const { RNSurveyMonkey } = NativeModules;

export default {
    init: (appName, hash) =>  RNSurveyMonkey.init(appName, hash),
    takeSurvey: (hash) =>  RNSurveyMonkey.takeSurvey(hash)
};
