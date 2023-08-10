package filter;

import model.UserModel;
import repository.RoleRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//urlPatterns: khi người dùng gọi link được quy định trong url pattens thì
// filter sẽ được kích hoạt
@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    private RoleRepository roleRepository = new RoleRepository();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//      Nơi quy dịnh rule
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (!req.getServletPath().startsWith("/login")) {
            if (req.getSession().getAttribute("checklogin") != null && req.getSession().getAttribute("user") != null) {
                if (req.getServletPath().startsWith("/incompetence") || req.getServletPath().startsWith("/home")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
                handleRequest(servletRequest, servletResponse, filterChain);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            if (req.getSession().getAttribute("checklogin") != null && req.getSession().getAttribute("user") != null) {
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    public void handleRequest(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String reqPath = req.getServletPath();
        UserModel user = (UserModel) req.getSession().getAttribute("user");
        String roleName = roleRepository.findById(user.getRoleId()).getName();
        switch (reqPath) {
            case "/user":
                if (roleName.equalsIgnoreCase("role_admin") || roleName.equalsIgnoreCase("role_manager"))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    resp.sendRedirect(req.getContextPath() + "/incompetence");
                break;
            case "/user/add":
                if (roleName.equalsIgnoreCase("role_admin") || roleName.equalsIgnoreCase("role_manager"))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    resp.sendRedirect(req.getContextPath() + "/incompetence");
                break;
            case "/role":
                if (roleName.equalsIgnoreCase("role_admin"))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    resp.sendRedirect(req.getContextPath() + "/incompetence");
                break;
            case "/role/add":
                if (roleName.equalsIgnoreCase("role_admin"))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    resp.sendRedirect(req.getContextPath() + "/incompetence");
                break;
            case "/project":
                if (roleName.equalsIgnoreCase("role_admin") || roleName.equalsIgnoreCase("role_manager"))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    resp.sendRedirect(req.getContextPath() + "/incompetence");
                break;
            case "/project/add":
                if (roleName.equalsIgnoreCase("role_admin") || roleName.equalsIgnoreCase("role_manager"))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    resp.sendRedirect(req.getContextPath() + "/incompetence");
                break;
            case "/job":
                if (roleName.equalsIgnoreCase("role_admin"))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    resp.sendRedirect(req.getContextPath() + "/incompetence");
                break;
            case "/job/add":
                if (roleName.equalsIgnoreCase("role_admin"))
                    filterChain.doFilter(servletRequest, servletResponse);
                else
                    resp.sendRedirect(req.getContextPath() + "/incompetence");
                break;
            default:
                filterChain.doFilter(servletRequest, servletResponse);
                break;
        }
    }

    @Override
    public void destroy() {
    }
}
