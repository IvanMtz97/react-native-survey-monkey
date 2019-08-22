
import { NativeModules } from 'react-native';

const { RNSurveyMonkey } = NativeModules;

export default {
    takeSurvey: (appName, hash) =>  RNSurveyMonkey.takeSurvey(appName, hash)
};
