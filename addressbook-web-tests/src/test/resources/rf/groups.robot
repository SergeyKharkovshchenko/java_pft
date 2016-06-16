*** Settings ***
Library    ru.stqa.pft.addressbook.rf.AddressbookKeywords
Suite Setup    Init Application Manager
Suite Teardown    Stop Application Manager

*** Test Cases ***
Can Create Group With Valid Data
    ${old count} =    Get Group Count
    Create Group    test name    tests header    test footer
    ${new count} =    Get Group Count
    ${expected count} =    Evaluate    ${old count} + 1
    Should Be Equal As Integers    ${new_count}    ${expected count}