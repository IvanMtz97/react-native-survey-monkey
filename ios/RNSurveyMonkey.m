#import "RNSurveyMonkey.h"
#import <React/RCTLog.h>
#import <SurveyMonkeyiOSSDK/SurveyMonkeyiOSSDK.h>

@interface RNSurveyMonkey () <SMFeedbackDelegate, UIAlertViewDelegate>
@property (nonatomic, strong) SMFeedbackViewController * feedbackController;
@end

@implementation RNSurveyMonkey
- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(takeSurvey:(NSString *)appName :(NSString *)hash)
{
    UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;



    _feedbackController = [[SMFeedbackViewController alloc] initWithSurvey:hash];
    _feedbackController.delegate = self;
    [[UINavigationBar appearance] setTintColor:[UIColor greenColor]];
    [_feedbackController scheduleInterceptFromViewController:rootViewController withAppTitle:appName];


    [_feedbackController presentFromViewController:rootViewController animated:YES completion:nil];
    NSLog(@"takeSurvey %@", hash);
}

- (void)respondentDidEndSurvey:(SMRespondent *)respondent error:(NSError *) error {
    self.feedbackController = nil;
}

@end
