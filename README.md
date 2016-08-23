Material Two Steps Login
======

An Android library that helps you to make a cool two steps login in Material Design way. Such as Google web login.

Download
--------

Download via Maven:

``
<dependency>
  <groupId>com.unipiazza.materialtwostepslogin</groupId>
  <artifactId>materialtwostepslogin</artifactId>
  <version>0.1.1</version>
  <type>pom</type>
</dependency>
``

or Gradle

```groovy
compile 'com.unipiazza.materialtwostepslogin:materialtwostepslogin:0.1.1'
```

Demo
-------------
See [Materialtwostepsdemo](https://github.com/unipiazza/unipiazza-android-twostepslogin/tree/master/materialtwostepsdemo) for code example.
See [Unipiazza](https://play.google.com/store/apps/details?id=com.unipiazza.utentiapp) in Play Store for a full working demo.

<img src="https://github.com/unipiazza/unipiazza-android-twostepslogin/blob/master/art/demo1.png" width="300">
<img src="https://github.com/unipiazza/unipiazza-android-twostepslogin/blob/master/art/demo2.png" width="300">

Usage
-------------
Create a new **Activity with MaterialTwoStepsLogin view in xml file**:

```xml
	<com.unipiazza.material2stepslogin.view.MaterialTwoStepsLogin
	android:id="@+id/login_view"
	android:layout_width="match_parent"
	android:layout_height="wrap_content" />
```

**Customize** it from Activity

```java
	MaterialTwoStepsLogin login_view = (MaterialTwoStepsLogin) findViewById(R.id.login_view);

	login_view.setListener(this);
	login_view.setActivity(this);
	login_view.setFirst_step_background_color(getResources().getColor(R.color.colorPrimary));
	login_view.setSecond_step_background_color(Color.WHITE);
	login_view.setLogo(R.mipmap.ic_launcher);
	login_view.setDescription(R.string.insert_email_login);
	login_view.setRegister_description(R.string.not_registered_login);
	login_view.setRegister_text(R.string.registrati);

	//Register Button background
	//login_view.setRegister_background(R.drawable.rounded_white_stroke_button);
	login_view.setButton_register_text_color(Color.WHITE);

	//EditText Backgounds
	//login_view.setEdittext_password_background();
	//login_view.setEdittext_email_background();

	//Button Backgrounds
	//login_view.setButton_login_background();
	//login_view.setButton_next_background();

	//Button Text Color
	login_view.setButton_next_text_color(Color.WHITE);
	login_view.setButton_login_text_color(getResources().getColor(R.color.colorPrimary));
```

Implement **Listener** and listen for actions


```java
	@Override
	public void onNextClicked(String email) {
	/*TODO Check if email is corrected
	    ...
	    if it is not call login_view.setNotVerified();
	*/
	login_view.setInfosAfterNext(email
		, "Material Two Steps Demo"
		, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
	}

	@Override
	public void onLoginClicked(String password) {
	/*TODO Check if password is corrected
	    ...
	    if it is not call login_view.setPasswordWrong();
	    otherwise go on with your Activities
	*/
	}

	@Override
	public void onRecoverPasswordClicked() {
	//Called when user click to recover password
	}

	@Override
	public void onBackToMail() {
	//Called when user click to back button
	}

	@Override
	public void onRegisterClicked() {
	//Called when user click to register
	}
```

Suggestion, help and pull request al welcome!

Libraries used
-------
[circleimageview](https://github.com/hdodenhof/CircleImageView)

[CircularReveal](https://github.com/ozodrukh/CircularReveal)

License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

see [LICENSE](https://github.com/unipiazza/unipiazza-android-twostepslogin/blob/master/LICENSE)

