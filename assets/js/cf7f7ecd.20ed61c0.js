"use strict";(self.webpackChunkfhircore=self.webpackChunkfhircore||[]).push([[805],{3905:(e,t,n)=>{n.d(t,{Zo:()=>c,kt:()=>u});var r=n(7294);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var s=r.createContext({}),p=function(e){var t=r.useContext(s),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},c=function(e){var t=p(e.components);return r.createElement(s.Provider,{value:t},e.children)},m={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,a=e.mdxType,o=e.originalType,s=e.parentName,c=l(e,["components","mdxType","originalType","parentName"]),d=p(n),u=a,g=d["".concat(s,".").concat(u)]||d[u]||m[u]||o;return n?r.createElement(g,i(i({ref:t},c),{},{components:n})):r.createElement(g,i({ref:t},c))}));function u(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var o=n.length,i=new Array(o);i[0]=d;var l={};for(var s in t)hasOwnProperty.call(t,s)&&(l[s]=t[s]);l.originalType=e,l.mdxType="string"==typeof e?e:a,i[1]=l;for(var p=2;p<o;p++)i[p]=n[p];return r.createElement.apply(null,i)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},6047:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>s,contentTitle:()=>i,default:()=>m,frontMatter:()=>o,metadata:()=>l,toc:()=>p});var r=n(7462),a=(n(7294),n(3905));const o={},i="Code Standards",l={unversionedId:"developer-setup/code-standards",id:"developer-setup/code-standards",title:"Code Standards",description:"Naming Conventions",source:"@site/docs/developer-setup/code-standards.mdx",sourceDirName:"developer-setup",slug:"/developer-setup/code-standards",permalink:"/developer-setup/code-standards",draft:!1,editUrl:"https://github.com/opensrp/fhircore/tree/main/docs/docs/developer-setup/code-standards.mdx",tags:[],version:"current",frontMatter:{},sidebar:"defaultSidebar",previous:{title:"Code Reviews",permalink:"/developer-setup/code-reviews"},next:{title:"Code Testing",permalink:"/developer-setup/code-testing"}},s={},p=[{value:"Naming Conventions",id:"naming-conventions",level:2},{value:"Branch Naming",id:"branch-naming",level:2},{value:"Commit Messages",id:"commit-messages",level:2}],c={toc:p};function m(e){let{components:t,...n}=e;return(0,a.kt)("wrapper",(0,r.Z)({},c,n,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h1",{id:"code-standards"},"Code Standards"),(0,a.kt)("h2",{id:"naming-conventions"},"Naming Conventions"),(0,a.kt)("ul",null,(0,a.kt)("li",{parentName:"ul"},"When using abbreviations in CamelCase, keep the first letter capitalized and lowercase the remaining letters. For example",(0,a.kt)("ul",{parentName:"li"},(0,a.kt)("li",{parentName:"ul"},'A "BMI Thresholds" variable name must be written as ',(0,a.kt)("inlineCode",{parentName:"li"},"bmiThresholds")),(0,a.kt)("li",{parentName:"ul"},'An "ANC Patient Repository" class name must be written as ',(0,a.kt)("inlineCode",{parentName:"li"},"AncPatientRespository"))))),(0,a.kt)("h2",{id:"branch-naming"},"Branch Naming"),(0,a.kt)("p",null,"Create a new branch using the following naming convention:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre"},"issue-number-feature-name\n")),(0,a.kt)("p",null,"For example:"),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre"},"238-fix-login-page-styling\n")),(0,a.kt)("h2",{id:"commit-messages"},"Commit Messages"),(0,a.kt)("p",null,"Here are some guidelines when writing a commit message:"),(0,a.kt)("ol",null,(0,a.kt)("li",{parentName:"ol"},"Separate subject/title from body with a blank line"),(0,a.kt)("li",{parentName:"ol"},"Limit the subject line to 50 characters"),(0,a.kt)("li",{parentName:"ol"},"Capitalize the subject line/Title"),(0,a.kt)("li",{parentName:"ol"},"Do not end the subject line with a period"),(0,a.kt)("li",{parentName:"ol"},"Use hyphens at the beginning of the commit messages in the body"),(0,a.kt)("li",{parentName:"ol"},"Use the imperative mood in the commit messages"),(0,a.kt)("li",{parentName:"ol"},"Wrap the body at 72 characters"),(0,a.kt)("li",{parentName:"ol"},"Use the body to explain what and why vs. how")),(0,a.kt)("p",null,(0,a.kt)("strong",{parentName:"p"},"Sample commit message:")),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre"},"Implement Login functionality\n\n- Add login page view\n- Implement authentication and credentials management\n- Add tests for login implementation\n- Fix sync bug causing crash on install\n")))}m.isMDXComponent=!0}}]);