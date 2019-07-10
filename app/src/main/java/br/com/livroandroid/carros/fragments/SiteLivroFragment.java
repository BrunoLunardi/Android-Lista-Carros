package br.com.livroandroid.carros.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.fragments.dialog.AboutDialog;

public class SiteLivroFragment extends BaseFragment {
    private static final String URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm";
    private WebView webView;
    private ProgressBar progress;
    //atualiza tela quando faz rolagem para baixo
    protected SwipeRefreshLayout swipeLayout;

    //cria o fragment_site_livro
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site_livro, container, false);
        webView = (WebView) view.findViewById(R.id.webview);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        setWebViewClient(webView);
        //Carrega a página
        webView.loadUrl(URL_SOBRE);

        //Swipe to Refresh
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        swipeLayout.setOnRefreshListener(OnRefreshListener());
        //Cores da animação
        swipeLayout.setColorSchemeResources(R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3
        );

        return view;
    }

    //Listener para atualizar a tela ao "rolar" ela para baixo
    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();//Atualiza a página
            }
        };
    }

    //webview -> site será exibido no app
    private void setWebViewClient(WebView webView) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView, String url, Bitmap favicon) {
                super.onPageStarted(webView, url, favicon);
                //Liga o progess
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                //Desliga o progress
                progress.setVisibility(View.INVISIBLE);
                //Termina a animação do Swipe to Refresh
                swipeLayout.setRefreshing(false);
            }

            //Intercepta requisições no webView
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("livroandroid", "webview url: " + url);
                if (url != null && url.endsWith("sobre.htm")) {
                    AboutDialog.showAbout(getFragmentManager());
                    //Retorna true para informar que interceptamos o evento
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

        });
    }

}