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
        Tokenizer tokenizer = new Tokenizer() ;
//        StringBuilder sb = new StringBuilder("<table><thead><tr><th>Surface form</th><th>Part-of-Speech</th><th>Base form</th><th>Reading Pronunciation</th></tr></thead>");

        if (text == null || text.isEmpty()) {
            System.out.println("No PathParam");
            text = "お寿司が食べたい。";
        }
        List<Token> tokens = tokenizer.tokenize(text);
        StringBuilder sb = new StringBuilder("<table>");
        sb.append("<thead><tr><th>Surface</th><th>AllFeatures</th></tr></thead>");
        sb.append("<tbody>");
        for (Token token : tokens) {
//            System.out.println(token.getSurface() + "\t" + token.getAllFeatures());
            sb.append("<tr>");
            sb.append("<td>");
            sb.append(token.getSurface());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(token.getAllFeatures());
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>");
        System.out.println(sb);
        return sb.toString();
//        return "Tokenized";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String tokenize() {
        Tokenizer tokenizer = new Tokenizer() ;
        List<Token> tokens = tokenizer.tokenize("お寿司が食べたい。");

        for (Token token : tokens) {
            System.out.println(token.getSurface() + "\t" + token.getAllFeatures());
        }
        return "<p>Success tokenize!</p>";
    }
}