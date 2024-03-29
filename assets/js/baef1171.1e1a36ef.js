"use strict";(self.webpackChunkfhircore=self.webpackChunkfhircore||[]).push([[1714],{3679:(e,n,i)=>{i.r(n),i.d(n,{assets:()=>l,contentTitle:()=>c,default:()=>h,frontMatter:()=>s,metadata:()=>d,toc:()=>o});var t=i(5893),r=i(1151);const s={title:"Profiles"},c="Profile configuration",d={id:"engineering/android-app/configuring/config-types/profile",title:"Profiles",description:"These configurations are used to control the content of the profile screen as well as how to render the profile UI. Typically for every register in application there is a corresponding profile. Technically the same implementation is used for all profiles, however the content configured.",source:"@site/docs/engineering/android-app/configuring/config-types/profile.mdx",sourceDirName:"engineering/android-app/configuring/config-types",slug:"/engineering/android-app/configuring/config-types/profile",permalink:"/engineering/android-app/configuring/config-types/profile",draft:!1,unlisted:!1,editUrl:"https://github.com/opensrp/fhircore/tree/main/docs/engineering/android-app/configuring/config-types/profile.mdx",tags:[],version:"current",frontMatter:{title:"Profiles"},sidebar:"defaultSidebar",previous:{title:"Navigation",permalink:"/engineering/android-app/configuring/config-types/navigation"},next:{title:"Questionnaires and forms",permalink:"/engineering/android-app/configuring/config-types/questionnaire"}},l={},o=[{value:"Working with dynamic data queries",id:"working-with-dynamic-data-queries",level:3},{value:"Config properties",id:"config-properties",level:2},{value:"Dynamic data pass between relatedResources",id:"dynamic-data-pass-between-relatedresources",level:2},{value:"Dynamic data pass between profiles and registers",id:"dynamic-data-pass-between-profiles-and-registers",level:2},{value:"Practitioner <strong>LAUNCH_PROFILE</strong>",id:"practitioner-launch_profile",level:3},{value:"household_config.json",id:"household_configjson",level:3},{value:"Dynamic data pass between profiles config properties",id:"dynamic-data-pass-between-profiles-config-properties",level:2},{value:"<strong>Working with contacts</strong>",id:"working-with-contacts",level:2},{value:"Practitioner calling a patient phone number",id:"practitioner-calling-a-patient-phone-number",level:2},{value:"Practitioner calling patient phone number config properties",id:"practitioner-calling-patient-phone-number-config-properties",level:2}];function a(e){const n={admonition:"admonition",code:"code",h1:"h1",h2:"h2",h3:"h3",li:"li",ol:"ol",p:"p",pre:"pre",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",...(0,r.a)(),...e.components};return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(n.h1,{id:"profile-configuration",children:"Profile configuration"}),"\n",(0,t.jsx)(n.p,{children:"These configurations are used to control the content of the profile screen as well as how to render the profile UI. Typically for every register in application there is a corresponding profile. Technically the same implementation is used for all profiles, however the content configured."}),"\n",(0,t.jsx)(n.admonition,{type:"info",children:(0,t.jsx)(n.p,{children:"For every register in the application there should at least be one profile configuration. Similar registers can re-use the same profile configuration."})}),"\n",(0,t.jsx)(n.h3,{id:"working-with-dynamic-data-queries",children:"Working with dynamic data queries"}),"\n",(0,t.jsx)(n.p,{children:"Assume you would like to filter resource data based on a criteria that needs computation before application. e.g show patients who are under 5 years or over 18 below, then this is the config to use."}),"\n",(0,t.jsxs)(n.p,{children:["Before you use this rule on a dataQuery, you need  to execute it first. The rules are executed within a ",(0,t.jsx)(n.code,{children:"configRules"})," block which follows rules engine standard and default priority of 1 which can be change based on requirement."]}),"\n",(0,t.jsx)(n.p,{children:"Below is a JSON config that shows how to execute rules. Refer to working with rules section."}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'{\n  "appId": "appId",\n  "configType": "register",\n  "id": "childRegister",\n  "configRules": [\n    {\n      "name": "under5",\n      "condition": "true",\n      "actions": [\n        "data.put(\'under5\', dateService.addOrSubtractYearFromCurrentDate(5,\'-\'))"\n      ]\n    }\n  ]\n}\n'})}),"\n",(0,t.jsx)(n.p,{children:"Below is a sample dataQuery config to filter register data by configRules."}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'"fhirResource": {\n  "baseResource": {\n    "resource": "Patient",\n    "dataQueries": [\n      {\n        "paramName": "birthdate",\n        "filterCriteria": [\n          {\n            "dataType": "DATE",\n            "computedRule": "under5",\n            "prefix": "GREATERTHAN_OR_EQUALS"\n          }\n        ]\n      }\n    ]\n  }\n}\n'})}),"\n",(0,t.jsx)(n.h2,{id:"config-properties",children:"Config properties"}),"\n",(0,t.jsxs)(n.table,{children:[(0,t.jsx)(n.thead,{children:(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.th,{children:"Property"}),(0,t.jsx)(n.th,{children:"Description"}),(0,t.jsx)(n.th,{style:{textAlign:"center"},children:"Required"}),(0,t.jsx)(n.th,{children:"Default"})]})}),(0,t.jsxs)(n.tbody,{children:[(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"appId"})}),(0,t.jsxs)(n.td,{children:[(0,t.jsx)(n.code,{children:"String"})," - unique identifier for the application"]}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"configType"})}),(0,t.jsx)(n.td,{children:"Type of configuration"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"profile"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"id"})}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"String"})}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"fhirResource"})}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"FhirResourceConfig"})}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"secondaryResources"})}),(0,t.jsxs)(n.td,{children:["List of ",(0,t.jsx)(n.code,{children:"FhirResourceConfig"}),"s"]}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"managingEntity"})}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"ManagingEntityConfig"})}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"profileParams"})}),(0,t.jsx)(n.td,{children:"A list Strings"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"rules"})}),(0,t.jsxs)(n.td,{children:["List of ",(0,t.jsx)(n.code,{children:"RuleConfig"}),"s"]}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"emptyList()"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"topAppBar"})}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"TopBarConfig"})}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"views"})}),(0,t.jsxs)(n.td,{children:["List of ",(0,t.jsx)(n.code,{children:"ViewProperties"})]}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"fabActions"})}),(0,t.jsxs)(n.td,{children:["List of ",(0,t.jsx)(n.code,{children:"NavigationMenuConfig"}),"s"]}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"emptyList()"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"overFlowMenuItems"})}),(0,t.jsxs)(n.td,{children:["List of ",(0,t.jsx)(n.code,{children:"OverflowMenuItemConfig"}),"s"]}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"emptyList()"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"filterActiveResources"})}),(0,t.jsxs)(n.td,{children:["List of ",(0,t.jsx)(n.code,{children:"ActiveResourceFilterConfig"}),"s"]}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"listOf(ActiveResourceFilterConfig(resourceType = ResourceType.Patient, active = true), ActiveResourceFilterConfig(resourceType = ResourceType.Group, active = true))"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"configRules"})}),(0,t.jsxs)(n.td,{children:["List of ",(0,t.jsx)(n.code,{children:"RuleConfig"}),"s"]}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"null"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"contentBackgroundColor"})}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"String"})}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"#FFFFFF"})})]})]})]}),"\n",(0,t.jsx)(n.h2,{id:"dynamic-data-pass-between-relatedresources",children:"Dynamic data pass between relatedResources"}),"\n",(0,t.jsx)(n.p,{children:"The related resources contains an array that lists related resources along the associated search parameters e.g Conditions ,Tasks and Careplans"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'    "relatedResources": [\n      {\n        "resource": "Condition",\n        "searchParameter": "subject"\n      },\n      {\n        "resource": "CarePlan",\n        "searchParameter": "subject"\n      },\n      {\n        "resource": "Task",\n        "searchParameter": "subject"\n      }\n    ]\n  \n'})}),"\n",(0,t.jsx)(n.h2,{id:"dynamic-data-pass-between-profiles-and-registers",children:"Dynamic data pass between profiles and registers"}),"\n",(0,t.jsxs)(n.p,{children:["For you to pass data between profiles you can make use of ",(0,t.jsx)(n.strong,{children:"action config params"})," which are executed when ",(0,t.jsx)(n.strong,{children:"LAUNCH_PROFILE"})," is invoked."]}),"\n",(0,t.jsxs)(n.p,{children:["Data extraction happens during rules execution and is persisted in ",(0,t.jsx)(n.code,{children:"computedValuesMap"})," which is later used to interpolate values annotated with ",(0,t.jsx)(n.code,{children:"@value"}),"."]}),"\n",(0,t.jsxs)(n.p,{children:["For example, assume the ",(0,t.jsx)(n.code,{children:"LAUNCH_PROFILE"})," ",(0,t.jsx)(n.code,{children:"onClick"})," function of ",(0,t.jsx)(n.code,{children:"practitioner_profile_config"})," takes you to ",(0,t.jsx)(n.code,{children:"household_profile"})," screen and you would like pass send ",(0,t.jsx)(n.code,{children:"practitionerId"})," from ",(0,t.jsx)(n.code,{children:"practitioner_profile_config"})," to ",(0,t.jsx)(n.code,{children:"household_profile"}),", define it as described below."]}),"\n",(0,t.jsxs)(n.h3,{id:"practitioner-launch_profile",children:["Practitioner ",(0,t.jsx)(n.strong,{children:"LAUNCH_PROFILE"})]}),"\n",(0,t.jsxs)(n.ol,{children:["\n",(0,t.jsx)(n.li,{children:"Write rules to extract the data you need."}),"\n"]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'"rules":[\n  {\n    "name": "practitionerId",\n    "condition": "true",\n    "actions": [\n      "data.put(\'practitionerId\', fhirPath.extractValue(Practitioner, \'Practitioner.id.replace(\\"Practitioner/\\",\\"\\")\').split(\\"/\\").get(0))"\n    ]\n  }\n]\n'})}),"\n",(0,t.jsxs)(n.ol,{start:"2",children:["\n",(0,t.jsxs)(n.li,{children:["Add your params in ",(0,t.jsx)(n.strong,{children:"LAUNCH_REGISTER"})," section of ",(0,t.jsx)(n.code,{children:"practition_register_config.json"})]}),"\n"]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'"actions": [\n  {\n    "trigger": "ON_CLICK",\n    "workflow": "LAUNCH_PROFILE",\n    "id": "practitionerProfile",\n    "params": [\n      {\n        "paramType": "PARAMDATA",\n        "key": "practitionerId",\n        "value": "@{practitionerId}"\n      }\n    ]\n  }\n]\n'})}),"\n",(0,t.jsx)(n.h3,{id:"household_configjson",children:"household_config.json"}),"\n",(0,t.jsxs)(n.p,{children:["A dataquery config to filter by ",(0,t.jsx)(n.code,{children:"practitionerId"}),". For more info refer to dataquery section."]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'{\n  "id": "householdQueryPractitionerId",\n  "filterType": "TOKEN",\n  "key": "_tag",\n  "valueType": "CODING",\n  "valueCoding": {\n    "system": "https://smartregister.org/",\n    "code": "@{practitionerId}"\n  }\n}\n'})}),"\n",(0,t.jsx)(n.h2,{id:"dynamic-data-pass-between-profiles-config-properties",children:"Dynamic data pass between profiles config properties"}),"\n",(0,t.jsxs)(n.table,{children:[(0,t.jsx)(n.thead,{children:(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.th,{children:"Property"}),(0,t.jsx)(n.th,{children:"Description"}),(0,t.jsx)(n.th,{style:{textAlign:"center"},children:"Required"}),(0,t.jsx)(n.th,{children:"Default"})]})}),(0,t.jsxs)(n.tbody,{children:[(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:"rules name"}),(0,t.jsx)(n.td,{children:"Unique identifier for the rules"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:"empty string"})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"condition"})}),(0,t.jsx)(n.td,{children:"specification of execution"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"false"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"actions"})}),(0,t.jsx)(n.td,{children:"an array of the rule logic with a fhirPathExpression"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"null"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"trigger"})}),(0,t.jsx)(n.td,{children:"application workflow action"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"workflow"})}),(0,t.jsx)(n.td,{children:"An application event that can trigger a workflow"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"null"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"params"})}),(0,t.jsx)(n.td,{children:"An array of actionParameters to pass to another profile"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{children:"emptyArray"})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"paramType"})}),(0,t.jsx)(n.td,{children:"Action ParameterType to use e.g PREPOPULATE OR PARAMDATA"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"null"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"key"})}),(0,t.jsx)(n.td,{children:"Action ParameterType unique key if defined but not tag is given"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"value"})}),(0,t.jsx)(n.td,{children:"Action ParameterType corresponding key's value"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]})]})]}),"\n",(0,t.jsx)(n.h2,{id:"working-with-contacts",children:(0,t.jsx)(n.strong,{children:"Working with contacts"})}),"\n",(0,t.jsx)(n.p,{children:"If you would like to store and display profile contacts.\nThe Person (e.g. Patient or Practitioner) has an extractable telecom value in their object body.\nBy extracting it you can configure which contact values to show on a Profile.\nHere is an example of an extraction rule that extracts a patient's phone number."}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'{\n    "name": "patientPhoneNumber",\n    "condition": "true",\n    "actions": [\n    "data.put(\'patientPhoneNumber\', fhirPath.extractValue(Patient, \'Patient.telecom[0].value\'))"\n    ]\n}\n'})}),"\n",(0,t.jsx)(n.h2,{id:"practitioner-calling-a-patient-phone-number",children:"Practitioner calling a patient phone number"}),"\n",(0,t.jsx)(n.p,{children:"If the patient's phone number has been extracted and displayed on the UI via a COMPOUND_TEXT\nviewType (or any other view type of your choice), the LAUNCH_DIALLER workflow is provided to allow the\nPractitioner to call the patient. The workflow passes the telephone number data to the device's dialler\nand from there, the Practitioner can proceed with the call."}),"\n",(0,t.jsx)(n.p,{children:"Below is an example configuration for the view type."}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'{\n    "viewType": "COMPOUND_TEXT",\n    "primaryText": "@{patientPhoneNumber}",\n    "primaryTextColor": "#0077CC",\n    "fontSize": 14,\n    "visible": "@{phoneNumberAvailable}",\n    "clickable": "true",\n    "primaryTextActions": [\n    {\n        "trigger": "ON_CLICK",\n        "workflow": "LAUNCH_DIALLER",\n        "params": [\n        {\n            "paramType": "PARAMDATA",\n            "key": "patientPhoneNumber",\n            "value": "@{patientPhoneNumber}"\n        }\n        ]\n    }\n    ]\n}\n'})}),"\n",(0,t.jsx)(n.p,{children:"Some notable configurations to enable the LAUNCH_DIALLER workflow include"}),"\n",(0,t.jsxs)(n.ol,{children:["\n",(0,t.jsxs)(n.li,{children:["\n",(0,t.jsxs)(n.p,{children:[(0,t.jsx)(n.code,{children:'clickable": "true"'})," The view type displaying the phone number must be clickable. This allows the\nON_CLICK trigger to be activated so it can call the ",(0,t.jsx)(n.code,{children:"LAUNCH_DIALLER"})," workflow."]}),"\n"]}),"\n",(0,t.jsxs)(n.li,{children:["\n",(0,t.jsxs)(n.p,{children:[(0,t.jsx)(n.code,{children:"visible: {RULE}"})," By configuring a rule that determines whether the phone number is visible,\nwe avoid showing the phone number when a Profile does not have one."]}),"\n"]}),"\n"]}),"\n",(0,t.jsx)(n.p,{children:"Here is an example of a rule to determine the availability of a phone number"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-json",children:'{\n    "name": "phoneNumberAvailable",\n    "condition": "true",\n    "actions": [\n    "data.put(\'phoneNumberAvailable\', !empty(data.get(\'patientPhoneNumber\')))"\n    ]\n}\n'})}),"\n",(0,t.jsxs)(n.p,{children:["The rule above checks the data map being maintained globally by the app and inserts a key ",(0,t.jsx)(n.code,{children:"phoneNumberAvailable"}),"\nwith a value based on whether or not ",(0,t.jsx)(n.code,{children:"patientPhoneNumber"})," is available in the same map."]}),"\n",(0,t.jsx)(n.h2,{id:"practitioner-calling-patient-phone-number-config-properties",children:"Practitioner calling patient phone number config properties"}),"\n",(0,t.jsxs)(n.p,{children:[(0,t.jsx)(n.code,{children:"clickable"})," and ",(0,t.jsx)(n.code,{children:"primaryTextActions"})," are relevant if using COMPOUND_TEXT to display the phone number"]}),"\n",(0,t.jsxs)(n.table,{children:[(0,t.jsx)(n.thead,{children:(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.th,{children:"Property"}),(0,t.jsx)(n.th,{children:"Description"}),(0,t.jsx)(n.th,{style:{textAlign:"center"},children:"Required"}),(0,t.jsx)(n.th,{children:"Default"})]})}),(0,t.jsxs)(n.tbody,{children:[(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:"rules name"}),(0,t.jsx)(n.td,{children:"Unique identifier for the rules"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:"empty string"})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"clickable"})}),(0,t.jsx)(n.td,{children:"set the clickability of the view type displaying the phone number"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"false"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"primaryTextActions"})}),(0,t.jsx)(n.td,{children:"an array of the rule logic with a fhirPathExpression"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"null"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"trigger"})}),(0,t.jsx)(n.td,{children:"application workflow action"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"workflow"})}),(0,t.jsx)(n.td,{children:"An application event that can trigger a workflow"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"null"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"params"})}),(0,t.jsx)(n.td,{children:"An array of actionParameters to pass to another profile"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{children:"emptyArray"})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"paramType"})}),(0,t.jsx)(n.td,{children:"Action ParameterType to use e.g PREPOPULATE OR PARAMDATA"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"No"}),(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"null"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"key"})}),(0,t.jsx)(n.td,{children:"Action ParameterType unique key if defined but not tag is given"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.code,{children:"value"})}),(0,t.jsx)(n.td,{children:"Action ParameterType corresponding key's value"}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"Yes"}),(0,t.jsx)(n.td,{})]})]})]})]})}function h(e={}){const{wrapper:n}={...(0,r.a)(),...e.components};return n?(0,t.jsx)(n,{...e,children:(0,t.jsx)(a,{...e})}):a(e)}},1151:(e,n,i)=>{i.d(n,{Z:()=>d,a:()=>c});var t=i(7294);const r={},s=t.createContext(r);function c(e){const n=t.useContext(s);return t.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function d(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(r):e.components||r:c(e.components),t.createElement(s.Provider,{value:n},e.children)}}}]);