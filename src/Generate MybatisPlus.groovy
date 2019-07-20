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

/*定义全局变量  类型映射 注解映射 表头*/
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
annonMap = [
        (~/id/)                                 : "@TableId(value = \"%s\", type = IdType.ID_WORKER_STR) @ApiModelProperty(value = \"%s\", hidden = true)",
        (~/created_by|created_date|order_no/)   : "@TableField(value = \"%s\", fill = FieldFill.INSERT) @ApiModelProperty(value = \"%s\", hidden = true)",
        (~/last_modified_by|last_modified_date/): "@TableField(value = \"%s\", fill = FieldFill.INSERT_UPDATE) @ApiModelProperty(value = \"%s\", hidden = true)",
        (~/enabled/)                            : "@TableField(value = \"%s\") @TableLogic(value = \"1\", delval = \"0\") @ApiModelProperty(value = \"%s\", hidden = true)",
        (~/version/)                            : "@TableField(value = \"%s\") @ApiModelProperty(value = \"%s\", hidden = true) @Version",
        (~/(?i)/)                               : "@TableField(value = \"%s\") @ApiModelProperty(value = \"%s\")"
]
apiModel = "@ApiModel(value = \"%s\")"
tableName = "@TableName(value = \"%s\")"

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    new File(dir, className + ".java").withPrintWriter('UTF-8') { out -> generate(out, table, className, fields) }
}

def generate(out, table, className, fields) {
    def modelAnno = String.format(apiModel, table.getComment())
    def taleAnno = String.format(tableName, table.getName())
    out.println "package $packageName"
    /* 可在此添加需要导入的包名，也可通过 IDE 批量修改生成的 Java 文件 */
    out.println ""
    out.println ""
    out.println "/** * @author jason */"
    out.println "${modelAnno}"
    out.println "${taleAnno}"
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
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).lookingAt() }.value
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

def gainComment(col) {
    def comment = col.getComment()
    if (StringUtils.isBlank(comment)) {
        comment = col.getName()
    }
    comment
}

def customAnnotation(col) {
    def comment = gainComment(col)
    def colName = Case.LOWER.apply(col.getName())
    def format = annonMap.find { p, t -> p.matcher(colName).lookingAt() }.value
    return String.format(format, col.getName(), comment)
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}