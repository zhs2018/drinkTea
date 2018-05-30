
//@RequestMapping("")
//@Controller
//public class EvaluationController extends BaseAction{
//	private static final long serialVersionUID = 1L;
//	@Autowired
//	private EvaluateProMapper evaluateProMapper;
//	@Autowired
//	private WebUserMapper webUserMapper;
//
//	/**
//	 * @发表评价
//	 * @return
//	 */
//	@RequestMapping("published")
//	public ModelAndView publishedEved(String content,Integer id){
//		ModelAndView mv = new ModelAndView();
//   System.out.println("输出的内容是："+content);
//   System.out.println("输出的ID是："+id);
//		return mv;
//
//	}



//	@RequestMapping("/evaluate")
//	public ModelAndView jumpToCommodityEvaluation(HttpSession session,EvaluatePro evaluatePro){
//		ModelAndView mv = new ModelAndView();
//	WebUser webUser = (WebUser) session.getAttribute("user");
//	if(null!=webUser){
//		List<EvaluatePro> list = evaluateProMapper.getListBy(evaluatePro);
//		List<EvaluatePro> list1 = new ArrayList<>();
//		for(EvaluatePro eva : list){
//			WebUser user = webUserMapper.selectByPrimaryKey(eva.getUserId());
//			if(null!=user){
//				eva.setContent(eva.getContent());
//				eva.setEvaluateTime(eva.getEvaluateTime());
//				eva.setWuName(user.getUserName());
//				eva.setImage(user.getImage());
//				list1.add(eva);
//			}
//		}
//		mv.addObject("resultList",list1);
//		mv.setViewName("weixin/commodityEvaluation");
//	}
//
//		return mv;
//	}



//}
