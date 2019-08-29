#import "RNSurveyMonkey.h"
#import <React/RCTLog.h>
#import <SurveyMonkeyiOSSDK/SurveyMonkeyiOSSDK.h>

@interface RNSurveyMonkey () <SMFeedbackDelegate, UIAlertViewDelegate>
@property (nonatomic, strong) SMFeedbackViewController * feedbackController;
@property (nonatomic, strong) RCTResponseSenderBlock callback;
@end

@implementation RNSurveyMonkey
- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(takeSurvey:(NSString *)appName :(NSString *)hash callback:(RCTResponseSenderBlock)callback )
{
    UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
    _feedbackController = [[SMFeedbackViewController alloc] initWithSurvey:hash];
    _feedbackController.delegate = self;
    [[UINavigationBar appearance] setTintColor:[UIColor greenColor]];
    [_feedbackController scheduleInterceptFromViewController:rootViewController withAppTitle:appName];
    [_feedbackController presentFromViewController:rootViewController animated:YES completion:nil];
    _callback = callback;
    NSLog(@"takeSurvey %@", hash);
}

- (void)respondentDidEndSurvey:(SMRespondent *)respondent error:(NSError *) error {
    BOOL wasCompleted = YES;
    if (error != nil) {
        // We are getting a non-OK result even for successful completes, thus failing only on cancellation
        if(error.code == 1 || [error.description containsString:@"The user canceled out of the survey."]){
            wasCompleted = NO;
        }
    }
    if(_callback != nil){
        _callback(@[wasCompleted ? @"YES" : @"NO"]);
    }
}

@end
