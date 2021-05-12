This file contains all the changes necessary in the XSDs to make them work in the DE4A context.

## dcterm.xsd


replaced

```xml
  <xsd:complexType name="MediaType">
    <xsd:complexContent>
        <xsd:extension base="xsd:anyType"/>   
    </xsd:complexContent>
  </xsd:complexType>
```

with

```xml
  <xsd:complexType name="MediaType">
    <!-- [ph] to create list of Object: added mixed=true -->
    <xsd:complexContent mixed="true">
        <xsd:extension base="xsd:anyType"/>   
    </xsd:complexContent>
  </xsd:complexType>
```


## dcat-ap.xsd

### first

replaced

```xml
  <xsd:complexType name="RoleType">
    <xsd:complexContent>
      <xsd:extension base="cbc:CodeType">
        <xsd:sequence/>
      </xsd:extension>
    </xsd:complexContent>
  </xsd:complexType>
```

with

```xml
  <xsd:complexType name="RoleType">
    <xsd:simpleContent>
      <xsd:extension base="cbc:CodeType" />
    </xsd:simpleContent>
  </xsd:complexType>
```

Background: underlying definition of CodeType:

```xml
 <complexType name="CodeType">
   <simpleContent>
     <extension base="<https://data.europe.eu/semanticassets/ns/cv/common/dataTypes-2.0.0#>CodeType" />
   </simpleContent>
 </complexType>
 ```
 