require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-survey-monkey"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  react-native-survey-monkey
                   DESC
  s.homepage     = "https://github.com/a-koka/react-native-survey-monkey/tree/bNirvana"
  s.license      = "MIT"
  s.authors      = { "Your Name" => "yourname@email.com" }
  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/a-koka/react-native-survey-monkey.git", :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,m,swift}"
  s.requires_arc = true

  s.dependency "React"
  s.dependency "surveymonkey-ios-sdk"
end

