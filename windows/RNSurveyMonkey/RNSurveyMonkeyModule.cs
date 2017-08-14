using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Com.Reactlibrary.RNSurveyMonkey
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNSurveyMonkeyModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNSurveyMonkeyModule"/>.
        /// </summary>
        internal RNSurveyMonkeyModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNSurveyMonkey";
            }
        }
    }
}
