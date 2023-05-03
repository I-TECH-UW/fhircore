"use strict";(self.webpackChunkfhircore=self.webpackChunkfhircore||[]).push([[90],{3905:(e,t,n)=>{n.d(t,{Zo:()=>c,kt:()=>m});var r=n(7294);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function a(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function l(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?a(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):a(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function i(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var s=r.createContext({}),p=function(e){var t=r.useContext(s),n=t;return e&&(n="function"==typeof e?e(t):l(l({},t),e)),n},c=function(e){var t=p(e.components);return r.createElement(s.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,o=e.mdxType,a=e.originalType,s=e.parentName,c=i(e,["components","mdxType","originalType","parentName"]),d=p(n),m=o,f=d["".concat(s,".").concat(m)]||d[m]||u[m]||a;return n?r.createElement(f,l(l({ref:t},c),{},{components:n})):r.createElement(f,l({ref:t},c))}));function m(e,t){var n=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var a=n.length,l=new Array(a);l[0]=d;var i={};for(var s in t)hasOwnProperty.call(t,s)&&(i[s]=t[s]);i.originalType=e,i.mdxType="string"==typeof e?e:o,l[1]=i;for(var p=2;p<a;p++)l[p]=n[p];return r.createElement.apply(null,l)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},1106:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>s,contentTitle:()=>l,default:()=>u,frontMatter:()=>a,metadata:()=>i,toc:()=>p});var r=n(7462),o=(n(7294),n(3905));const a={},l="Code Testing",i={unversionedId:"developer-setup/code-testing",id:"developer-setup/code-testing",title:"Code Testing",description:"Unit Tests",source:"@site/docs/developer-setup/code-testing.mdx",sourceDirName:"developer-setup",slug:"/developer-setup/code-testing",permalink:"/developer-setup/code-testing",draft:!1,editUrl:"https://github.com/opensrp/fhircore/tree/main/docs/docs/developer-setup/code-testing.mdx",tags:[],version:"current",frontMatter:{},sidebar:"defaultSidebar",previous:{title:"Code Standards",permalink:"/developer-setup/code-standards"},next:{title:"Keycloak Auth Token Configuration",permalink:"/developer-setup/keycloak-auth-token-config"}},s={},p=[{value:"Unit Tests",id:"unit-tests",level:2},{value:"Naming Conventions",id:"naming-conventions",level:3},{value:"Spotless",id:"spotless",level:2}],c={toc:p};function u(e){let{components:t,...n}=e;return(0,o.kt)("wrapper",(0,r.Z)({},c,n,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("h1",{id:"code-testing"},"Code Testing"),(0,o.kt)("h2",{id:"unit-tests"},"Unit Tests"),(0,o.kt)("h3",{id:"naming-conventions"},"Naming Conventions"),(0,o.kt)("p",null,"We follow the following naming convention for our test cases:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"<methodName><ConditionUnderTest><ExpectedBehavior>\n")),(0,o.kt)("p",null,(0,o.kt)("strong",{parentName:"p"},"Example:")," for a sample method:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},"fun isNumberEven(value:Int):Bool{\n")),(0,o.kt)("p",null,"Sample test case:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"isNumberEvenWithParameterFiveShouldReturnFalse\n")),(0,o.kt)("h2",{id:"spotless"},"Spotless"),(0,o.kt)("p",null,"We use Spotless to maintain the Java/Kotlin coding style in the codebase. Run the following command to check the codebase:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"./gradlew spotlessCheck\n")),(0,o.kt)("p",null,"and run the following command to apply fixes to the violations:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"./gradlew spotlessApply\n")))}u.isMDXComponent=!0}}]);