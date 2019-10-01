package Pages.Admin

import Admin.modules.ContactsTableRows

class ContactsPage extends BaseAppPage {
  static at = {isAngularReady()}
  static url = 'http://localhost:4200/admin/contacts'
  static content = {
    searchField { $('#keywordInput') }
    searchButton { $('button[type=submit]') }
    showAll(wait:true) { $("a",text: startsWith("Show All")) }
    contactList (wait:true){
        $('table tr').tail().moduleList(ContactsTableRows) // tailing to skip header row 
    }
  }

  void clickEditContact() {
    contactList[0].clickAction()
  }

  Boolean checkContact(String firstName, String lastName, String org){
    for (contact in contactList) {
      if(contact.name.text() == firstName + " " + lastName && contact.organization.text() == org){
        // println("VERIFIED CONTACT")
        return true
      }
    }
    return false

  }

  void clickSearchButton() {
    searchButton.click()
  }

  void setSearchTerms(String searchTerms) {
    searchField.value(searchTerms)
  }

  void clickShowAll(){
    showAll.click()
  }

}