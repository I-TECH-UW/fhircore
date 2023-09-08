"use strict";(self.webpackChunkfhircore=self.webpackChunkfhircore||[]).push([[971],{3905:(e,t,a)=>{a.d(t,{Zo:()=>d,kt:()=>m});var r=a(7294);function n(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function o(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,r)}return a}function i(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?o(Object(a),!0).forEach((function(t){n(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):o(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function l(e,t){if(null==e)return{};var a,r,n=function(e,t){if(null==e)return{};var a,r,n={},o=Object.keys(e);for(r=0;r<o.length;r++)a=o[r],t.indexOf(a)>=0||(n[a]=e[a]);return n}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)a=o[r],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(n[a]=e[a])}return n}var s=r.createContext({}),p=function(e){var t=r.useContext(s),a=t;return e&&(a="function"==typeof e?e(t):i(i({},t),e)),a},d=function(e){var t=p(e.components);return r.createElement(s.Provider,{value:t},e.children)},c={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},h=r.forwardRef((function(e,t){var a=e.components,n=e.mdxType,o=e.originalType,s=e.parentName,d=l(e,["components","mdxType","originalType","parentName"]),h=p(a),m=n,u=h["".concat(s,".").concat(m)]||h[m]||c[m]||o;return a?r.createElement(u,i(i({ref:t},d),{},{components:a})):r.createElement(u,i({ref:t},d))}));function m(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var o=a.length,i=new Array(o);i[0]=h;var l={};for(var s in t)hasOwnProperty.call(t,s)&&(l[s]=t[s]);l.originalType=e,l.mdxType="string"==typeof e?e:n,i[1]=l;for(var p=2;p<o;p++)i[p]=a[p];return r.createElement.apply(null,i)}return r.createElement.apply(null,a)}h.displayName="MDXCreateElement"},1269:(e,t,a)=>{a.r(t),a.d(t,{assets:()=>s,contentTitle:()=>i,default:()=>c,frontMatter:()=>o,metadata:()=>l,toc:()=>p});var r=a(7462),n=(a(7294),a(3905));const o={sidebar_position:1,sidebar_label:"Overview"},i="Overview",l={unversionedId:"index",id:"index",title:"Overview",description:"OpenSRP is a FHIR-native medical record system for health workers to manage their patients for child health, maternal health and more. OpenSRP is developed by Ona, a Kenyan social enterprise, with contributions from organizations around the world.",source:"@site/docs/index.md",sourceDirName:".",slug:"/",permalink:"/",draft:!1,editUrl:"https://github.com/opensrp/fhircore/tree/main/docs/docs/index.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1,sidebar_label:"Overview"},sidebar:"defaultSidebar",next:{title:"Project Information",permalink:"/project-information/"}},s={},p=[{value:"OpenSRP Android App",id:"opensrp-android-app",level:4},{value:"OpenSRP Admin Dashboard",id:"opensrp-admin-dashboard",level:4},{value:"OpenSRP Analytics Dashboard",id:"opensrp-analytics-dashboard",level:4},{value:"Technology",id:"technology",level:4}],d={toc:p};function c(e){let{components:t,...o}=e;return(0,n.kt)("wrapper",(0,r.Z)({},d,o,{components:t,mdxType:"MDXLayout"}),(0,n.kt)("h1",{id:"overview"},"Overview"),(0,n.kt)("p",null,"OpenSRP is a FHIR-native medical record system for health workers to manage their patients for child health, maternal health and more. OpenSRP is developed by Ona, a Kenyan social enterprise, with contributions from organizations around the world."),(0,n.kt)("p",null,"OpenSRP 2, released in 2023, combines WHO health workflows with FHIR to transform how healthcare is delivered and managed."),(0,n.kt)("p",null,"OpenSRP 2 has three parts: a mobile app for Android devices, a web-based Admin Dashboard, and an Analytics Dashboard."),(0,n.kt)("h4",{id:"opensrp-android-app"},"OpenSRP Android App"),(0,n.kt)("p",null,(0,n.kt)("img",{src:a(850).Z,width:"1280",height:"826"})),(0,n.kt)("p",null,"The OpenSRP Android app is used by health workers to:"),(0,n.kt)("ol",null,(0,n.kt)("li",{parentName:"ol"},"Enroll community members to a medical record system."),(0,n.kt)("li",{parentName:"ol"},"Turn community members into patients by adding them to a care plan associated with their condition."),(0,n.kt)("li",{parentName:"ol"},"Set future services a patient should receive based on their care plan, and assign making sure the services happen to health team members as tasks."),(0,n.kt)("li",{parentName:"ol"},"Clearly visualize overdue patients so the health team can return them to care."),(0,n.kt)("li",{parentName:"ol"},"Track performance of providing services to patients at individual health care practitioner and facility levels.")),(0,n.kt)("p",null,(0,n.kt)("a",{parentName:"p",href:"/about-opensrp/app-features"},"Android App features")),(0,n.kt)("h4",{id:"opensrp-admin-dashboard"},"OpenSRP Admin Dashboard"),(0,n.kt)("p",null,"The OpenSRP Admin Dashboard is used by health administrators and project managers to:"),(0,n.kt)("ol",null,(0,n.kt)("li",{parentName:"ol"},"Add, edit and remove health worker user accounts."),(0,n.kt)("li",{parentName:"ol"},"Manage health team organization structure such. as locations, facilities, and line of reporting."),(0,n.kt)("li",{parentName:"ol"},"View patient information.")),(0,n.kt)("p",null,(0,n.kt)("a",{parentName:"p",href:"/about-opensrp/admin-dashboard-features"},"Admin Dashboard features")),(0,n.kt)("h4",{id:"opensrp-analytics-dashboard"},"OpenSRP Analytics Dashboard"),(0,n.kt)("p",null,"The OpenSRP Analytics Dashboard is used by health administrators, project managers, and M&E analysts to:"),(0,n.kt)("ol",null,(0,n.kt)("li",{parentName:"ol"},"View reporting at many aggregation levels, from system wide down to a single health worker."),(0,n.kt)("li",{parentName:"ol"},"Access to data warehouse.")),(0,n.kt)("h4",{id:"technology"},"Technology"),(0,n.kt)("p",null,"The Android app is primarily written in Kotlin\u200b, architected as a FHIR-native platform, and powered by ",(0,n.kt)("a",{parentName:"p",href:"https://github.com/google/android-fhir"},"Android FHIR SDK"),"."),(0,n.kt)("p",null,"OpenSRP on Github: "),(0,n.kt)("ul",null,(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"https://github.com/opensrp/fhircore"},"Android App Github")),(0,n.kt)("li",{parentName:"ul"},(0,n.kt)("a",{parentName:"li",href:"https://github.com/opensrp/web"},"Admin Dashboard Github"))),(0,n.kt)("p",null,"In addition to the Android App, Admin Dashboard, and Analytics Dashboard, OpenSRP uses a number of tools as described in the diagram below:"),(0,n.kt)("p",null,(0,n.kt)("img",{src:a(3541).Z,width:"2090",height:"917"})))}c.isMDXComponent=!0},3541:(e,t,a)=>{a.d(t,{Z:()=>r});const r=a.p+"assets/images/opensrp-platform-flow-3c5b9c409a57d06a21c31afa2f0e6aa4.png"},850:(e,t,a)=>{a.d(t,{Z:()=>r});const r=a.p+"assets/images/overview-app-4ea486760eeafb43ba87f1da12835b0e.png"}}]);