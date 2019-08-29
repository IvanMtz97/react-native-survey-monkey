import { NativeModules } from 'react-native';

const { RNSurveyMonkey } = NativeModules;

export default {
  takeSurvey: (appName, hash, callback) => RNSurveyMonkey.takeSurvey(appName, hash, callback)
};
