<#function camelCaseInStringaNormale string>
    <#return string?replace('(\\p{Upper})', ' $1', 'r')?trim?capitalize>
</#function>