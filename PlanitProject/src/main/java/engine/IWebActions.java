package engine;

import webinteractivies.Click;
import webinteractivies.Navigate;
import webinteractivies.Select;
import webinteractivies.Set;
import webinteractivies.Validate;

public interface IWebActions {
     Validate validate = new Validate();
     Click click = new Click();
     Select select = new Select();
     Navigate navigate = new Navigate();
     Set set=new Set();
}
