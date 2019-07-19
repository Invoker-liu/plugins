import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil
import org.apache.commons.lang3.StringUtils

/*
* Available context bindings:
*   SELECTION   Iterable<DasObject>
*   PROJECT     project
*   FILES       files helper
*/

/*定义全局变量 包名以及字段映射Map等 */
packageName = "com.oppo.csc.ddp.decision.pojo.po;"
typeMapping = [
        (~/bigint/)                       : "Long",
        (~/int/)                          : "Integer",
        (~/number\(\*\)/)                 : "Integer",
        (~/(?i)float|double|decimal|real/): "double",
        (~/(?i)datetime|timestamp/)       : "LocalDateTime",
        (~/(?i)date/)                     : "LocalDate",
        (~/(?i)time/)                     : "LocalDateTime",
        (~/(?i)number/)                   : "Long",
        (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    new File(dir, className + ".java").withPrintWriter('UTF-8') { out -> generate(out, table, className, fields) }
}

def generate(out, table, className, fields) {
    out.println "package $packageName"
    /* 可在此添加需要导入的包名，也可通过 IDE 批量修改生成的 Java 文件 */
    out.println ""
    out.println ""
    out.println "/** * @author jason */"
    out.println "@ApiModel(value = \"${table.getComment()}\")"
    out.println "@TableName(value = \"${table.getName()}\")"
    out.println "public class $className {"
    out.println ""
    fields.each() {
        out.println "/** * ${it.comment} */"
        if (it.annos != "") out.println "  ${it.annos}"
        out.println "  private ${it.type} ${it.name};"
        out.println ""
    }

    out.println ""
    fields.each() {
        out.println "public static final String COL_${it.uppercol} = \"${it.col}\" ;"
        out.println ""
    }

    out.println ""
    fields.each() {
        out.println ""
        out.println "  public ${it.type} get${it.name.capitalize()}() {"
        out.println "    return ${it.name};"
        out.println "  }"
        out.println ""
        out.println "  public void set${it.name.capitalize()}(${it.type} ${it.name}) {"
        out.println "    this.${it.name} = ${it.name};"
        out.println "  }"
        out.println ""
    }
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           name    : javaName(col.getName(), false),
                           type    : typeStr,
                           col     : col.getName(),
                           comment : gainComment(col),
                           annos   : customAnnotation(col),
                           uppercol: Case.UPPER.apply(col.getName())
                   ]]
    }
}

static def gainComment(col) {
    def comment = col.getComment()
    if (StringUtils.isBlank(comment)) {
        comment = col.getName()
    }
    comment
}

static def customAnnotation(col) {
    def comment = col.getComment()
    if (null == comment || "" == comment) {
        comment = col.getName()
    }
    if ("id" == col.getName()) {
        return "@TableId(value = \"" + col.getName() + "\" , type = IdType.ID_WORKER_STR) \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    if ("pm_id" == col.getName()) {
        return "@TableId(value = \"" + col.getName() + "\" , type = IdType.INPUT) \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    if ("created_by" == col.getName()) {
        return "@TableField(value = \"" + col.getName() + "\", fill = FieldFill.INSERT) \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    if ("created_date" == col.getName()) {
        return "@TableField(value = \"" + col.getName() + "\", fill = FieldFill.INSERT) \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    if ("last_modified_by" == col.getName()) {
        return "@TableField(value = \"" + col.getName() + "\", fill = FieldFill.INSERT_UPDATE) \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    if ("last_modified_date" == col.getName()) {
        return "@TableField(value = \"" + col.getName() + "\", fill = FieldFill.INSERT_UPDATE)  \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    if ("enabled" == col.getName()) {
        return "@TableField(value = \"" + col.getName() + "\") \n @TableLogic(value = \"1\", delval = \"0\") \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    if ("version" == col.getName()) {
        return "@TableField(value = \"" + col.getName() + "\") \n  @Version \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    if ("order_no" == col.getName()) {
        return "@TableField(value = \"" + col.getName() + "\", fill = FieldFill.INSERT) \n  @Version \n @ApiModelProperty(value = \"" + comment + "\")"
    }
    return "@TableField(value = \"" + col.getName() + "\") \n @ApiModelProperty(value = \"" + comment + "\")"
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}