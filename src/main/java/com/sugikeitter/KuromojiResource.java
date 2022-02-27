package com.sugikeitter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.List;



@Path("/tokenizeKuromoji")
public class KuromojiResource {
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{text}")
    public String tokenizeFromPath(@PathParam String text) {
        System.out.println("input: " + text);
        if (text == null || text.isEmpty()) {
            System.out.println("No PathParam");
            text = "お寿司が食べたい。";
        }
        Tokenizer tokenizer = new Tokenizer() ;
        List<Token> tokens = tokenizer.tokenize(text);
        StringBuilder htmlResult = new StringBuilder();
        htmlResult.append("<html>")
                .append("<head>")
                .append("<style type=\"text/css\">")
                .append(css)
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<table>")
                .append("<thead><tr><th>Surface</th><th>Part-of-Speech</th><th>Base form</th><th>Reading Pronunciation</th></tr></thead>")
                .append("<tbody>");
        for (Token token : tokens) {
            htmlResult.append("<tr>")
                    .append("<td>")
                    .append(token.getSurface())
                    .append("</td>")
                    .append("<td>")
                    .append(token.getAllFeaturesArray()[0])
                    .append(",")
                    .append(token.getAllFeaturesArray()[1])
                    .append(",")
                    .append(token.getAllFeaturesArray()[2])
                    .append(",")
                    .append(token.getAllFeaturesArray()[3])
                    .append("</td>")
                    .append("<td>")
                    .append(token.getAllFeaturesArray()[6])
                    .append("</td>")
                    .append("<td>")
                    .append(token.getAllFeaturesArray()[7])
                    .append("</td>")
                    .append("</tr>");
        }
        htmlResult.append("</tbody>")
                .append("</table>")
                .append("</body>")
                .append("</html>");
//        System.out.println(htmlResult);
        return htmlResult.toString();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String tokenize() {
        Tokenizer tokenizer = new Tokenizer() ;
        List<Token> tokens = tokenizer.tokenize("お寿司が食べたい。");

        for (Token token : tokens) {
            System.out.println(token.getSurface() + "\t" + token.getAllFeatures());
        }
        return "<p>Usage: /tokenizeKuromoji/{TEXT_YOU_WANT_TO_TOKENIZE}}</p>";
    }

    private static final String css = "body {\n" +
            "  font-family: \"Open Sans\", sans-serif;\n" +
            "  line-height: 1.25;\n" +
            "}\n" +
            "\n" +
            "table {\n" +
            "  border-collapse: collapse;\n" +
            "  margin: 0 auto;\n" +
            "  padding: 0;\n" +
            "  width: 650px;\n" +
            "  table-layout: fixed;\n" +
            "  color: #000;\n" +
            "}\n" +
            "\n" +
            "table tr {\n" +
            "  background-color: #f7f7f7;\n" +
            "  padding: .35em;\n" +
            "  border-bottom: 1px solid #bbb;\n" +
            "}\n" +
            "table thead{\n" +
            "  border-bottom: 2px solid #67a83b;\n" +
            "  color: #67a83b;\n" +
            "}\n" +
            "table tr:last-child{\n" +
            "   border-bottom: none\n" +
            "}\n" +
            "table th,\n" +
            "table td {\n" +
            "  padding: 1em 10px 1em 1em;\n" +
            "  border-right: 1px solid #bbb;\n" +
            "}\n" +
            "table th:last-child,\n" +
            "table td:last-child{\n" +
            "    border: none;\n" +
            "}\n" +
            "tbody th {\n" +
            "    color: #ff9901;\n" +
            "}\n" +
            ".txt{\n" +
            "   text-align: left;\n" +
            "   font-size: .85em;\n" +
            "}\n" +
            ".price{\n" +
            "   text-align: right;\n" +
            "}\n" +
            "@media screen and (max-width: 600px) {\n" +
            "  table {\n" +
            "    border: 0;\n" +
            "    width:100%\n" +
            "  }\n" +
            "  table th{\n" +
            "    display: block;\n" +
            "    border-right: none;\n" +
            "    border-bottom: 5px solid #ff9901;\n" +
            "    padding-bottom: .6em;\n" +
            "    margin-bottom: .6em;\n" +
            "\n" +
            "  }\n" +
            "  table thead {\n" +
            "    border: none;\n" +
            "    clip: rect(0 0 0 0);\n" +
            "    height: 1px;\n" +
            "    margin: -1px;\n" +
            "    overflow: hidden;\n" +
            "    padding: 0;\n" +
            "    position: absolute;\n" +
            "    width: 1px;\n" +
            "  }\n" +
            "  \n" +
            "  table tr {\n" +
            "    display: block;\n" +
            "    margin-bottom: 2em;\n" +
            "  }\n" +
            "  \n" +
            "  table td {\n" +
            "    border-bottom: 1px solid #bbb;\n" +
            "    display: block;\n" +
            "    font-size: .8em;\n" +
            "    text-align: right;\n" +
            "    position: relative;\n" +
            "    padding: .625em .625em .625em 4em;\n" +
            "    border-right: none;\n" +
            "  }\n" +
            "  \n" +
            "  table td::before {\n" +
            "    content: attr(data-label);\n" +
            "    font-weight: bold;\n" +
            "    position: absolute;\n" +
            "    left: 10px;\n" +
            "  }\n" +
            "  \n" +
            "  table td:last-child {\n" +
            "    border-bottom: 0;\n" +
            "  }\n" +
            "}";
}