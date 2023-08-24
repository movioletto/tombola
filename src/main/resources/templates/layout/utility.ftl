<#function camelCaseInStringaNormaleSenzaCapitalize string>
    <#return string?replace('(\\p{Upper})', ' $1', 'r')?trim>
</#function>

<#function stringaInCamelCase string>
    <#local words = string?split(" ")>
    <#local result = "">
    <#list words as word>
        <#if word_index == 0>
            <#local result = result + word?uncap_first>
        <#else>
            <#local result = result + word?cap_first>
        </#if>
    </#list>
    <#return result>
</#function>

<#function camelCaseInStringaNormale string>
    <#return camelCaseInStringaNormaleSenzaCapitalize(string)?capitalize>
</#function>

<#function nomeAnimaleDaNomeGiocatore string>
    <#assign nomeAnimale = camelCaseInStringaNormaleSenzaCapitalize(string)>
    <#return stringaInCamelCase(nomeAnimale?substring(0, nomeAnimale?last_index_of(" ")))>
</#function>

<#function almenoUnaLetteraUppercase(string)>
    <#list string?split("") as char>
        <#if char?matches("[A-Z]")>
            <#return true>
        </#if>
    </#list>
    <#return false>
</#function>

<#function isNomeAnimale string animaleList>
    <#if almenoUnaLetteraUppercase(string) && animaleList?size gt 0>
        <#return animaleList?seq_contains(nomeAnimaleDaNomeGiocatore(string))>
    </#if>
    <#return false>
</#function>